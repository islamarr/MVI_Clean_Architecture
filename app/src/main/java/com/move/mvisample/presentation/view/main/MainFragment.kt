package com.move.mvisample.presentation.view.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.move.mvisample.R
import com.move.mvisample.core.COLUMNS_COUNT
import com.move.mvisample.core.gone
import com.move.mvisample.core.visible
import com.move.mvisample.databinding.MainFragmentBinding
import com.move.mvisample.presentation.view.base.BaseFragment
import com.move.mvisample.presentation.view.main.adapter.CarsAdapter
import com.move.mvisample.presentation.viewmodel.main.MainActions
import com.move.mvisample.presentation.viewmodel.main.MainStates
import com.move.mvisample.presentation.viewmodel.main.MainViewModel
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

    private fun setClickListeners() {
        binding?.retryBtn?.setOnClickListener {
            loadCarImages()
        }
    }

    private fun loadCarImages() {
        viewModel.dispatch(MainActions.LoadImages(id = "332199935"))
    }

    private fun initRecyclerView() {
        binding?.list?.apply {  //TODO replace with grid layout
            carsAdapter = CarsAdapter()
            layoutManager = GridLayoutManager(requireActivity(), COLUMNS_COUNT)
            adapter = carsAdapter
        }
    }

    private fun showEmptyList(show: Boolean) {
        binding?.loadingProgressBar?.gone()
        binding?.emptyList?.isVisible = show
        binding?.list?.isVisible = !show
    }

    private fun startObserver() {
        lifecycleScope.launch {
            viewModel.state.collect {
                handleViewState(it)
            }
        }
    }

    private fun handleViewState(it: MainStates) {
        when (it) {
            is MainStates.Idle -> Log.d(TAG, "Idle: ")
            is MainStates.CarImagesLoaded -> {
                if (it.carImageURLList.isEmpty()) {
                    showEmptyList(true)
                    binding?.retryBtn?.gone()
                    binding?.emptyListText?.text = getString(R.string.empty_list)
                } else {
                    showEmptyList(false)
                    carsAdapter.submitList(it.carImageURLList)
                }
            }
            is MainStates.ShowERRORMessage -> {
                showEmptyList(true)
                binding?.emptyListText?.text = it.reason
            }
            is MainStates.Loading -> binding?.loadingProgressBar?.visible()
        }
    }


}