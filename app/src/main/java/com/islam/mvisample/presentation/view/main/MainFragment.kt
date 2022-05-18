package com.islam.mvisample.presentation.view.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.islam.mvisample.R
import com.islam.mvisample.common.CAR_LIST_ID
import com.islam.mvisample.common.COLUMNS_COUNT
import com.islam.mvisample.common.gone
import com.islam.mvisample.common.visible
import com.islam.mvisample.databinding.MainFragmentBinding
import com.islam.mvisample.presentation.view.base.BaseFragment
import com.islam.mvisample.presentation.view.main.adapter.CarsAdapter
import com.islam.mvisample.presentation.viewmodel.main.MainActions
import com.islam.mvisample.presentation.viewmodel.main.MainStates
import com.islam.mvisample.presentation.viewmodel.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val TAG = "MainFragment"

@AndroidEntryPoint
class MainFragment : BaseFragment<MainFragmentBinding>() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var carsAdapter: CarsAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> MainFragmentBinding
        get() = MainFragmentBinding::inflate

    override fun setupOnViewCreated(view: View) {
        initRecyclerView()
        startObserver()
        loadCarImages()
        setClickListeners()
    }

    private fun initRecyclerView() {
        binding.list.apply {
            carsAdapter = CarsAdapter()
            layoutManager = GridLayoutManager(requireActivity(), COLUMNS_COUNT)
            adapter = carsAdapter
        }
    }

    private fun startObserver() {
        lifecycleScope.launch {
            viewModel.state.collect {
                handleViewState(it)
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
            is MainStates.Idle -> Log.d(TAG, "Idle")
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