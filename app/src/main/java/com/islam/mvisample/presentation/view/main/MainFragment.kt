package com.islam.mvisample.presentation.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.islam.mvisample.R
import com.islam.mvisample.common.CAR_LIST_ID
import com.islam.mvisample.common.COLUMNS_SPACE
import com.islam.mvisample.common.gone
import com.islam.mvisample.common.visible
import com.islam.mvisample.databinding.MainFragmentBinding
import com.islam.mvisample.presentation.view.base.BaseFragment
import com.islam.mvisample.presentation.view.main.adapter.CarsAdapter
import com.islam.mvisample.presentation.view.main.adapter.GridSpacingItemDecoration
import com.islam.mvisample.presentation.viewmodel.main.MainActions
import com.islam.mvisample.presentation.viewmodel.main.MainStates
import com.islam.mvisample.presentation.viewmodel.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<MainFragmentBinding>() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var carsAdapter: CarsAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> MainFragmentBinding
        get() = MainFragmentBinding::inflate

    override fun setupOnViewCreated(view: View) {
        initRecyclerView()
        startObserver()
        setClickListeners()
    }

    private fun initRecyclerView() {
        val initialImagesDimens = resources.getInteger(R.integer.initial_images_dimens)
        binding.list.apply {
            carsAdapter = CarsAdapter(initialImagesDimens)
            val columnsCount = resources.getInteger(R.integer.columns_count)
            layoutManager = GridLayoutManager(requireActivity(), columnsCount)
            adapter = carsAdapter
            addItemDecoration(GridSpacingItemDecoration(columnsCount, COLUMNS_SPACE))
        }
    }

    private fun startObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    handleViewState(it)
                }
            }
        }
    }

    private fun setClickListeners() {
        binding.retryBtn.setOnClickListener {
            loadCarImages()
        }
    }

    private fun loadCarImages() {
        viewModel.dispatch(MainActions.LoadImages(id = CAR_LIST_ID))
    }

    private fun showEmptyList(show: Boolean) {
        binding.loadingProgressBar.gone()
        binding.emptyListGroup.isVisible = show
        binding.list.isVisible = !show
    }

    private fun handleViewState(it: MainStates) {
        when (it) {
            is MainStates.Idle -> loadCarImages()
            is MainStates.Loading -> binding.loadingProgressBar.visible()
            is MainStates.CarImagesLoaded -> {
                showEmptyList(false)
                carsAdapter.submitList(it.carImageURLList)
            }
            is MainStates.EmptyCarList -> {
                showEmptyList(true)
                binding.retryBtn.gone()
                binding.emptyListText.text = getString(R.string.empty_list)
            }
            is MainStates.ShowErrorMessage -> {
                showEmptyList(true)
                binding.emptyListText.text = getString(R.string.error_message)
            }
        }
    }


}