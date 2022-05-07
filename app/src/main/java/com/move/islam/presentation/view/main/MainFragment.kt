package com.move.islam.presentation.view.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.move.islam.R
import com.move.islam.core.gone
import com.move.islam.databinding.MainFragmentBinding
import com.move.islam.presentation.view.base.BaseFragment
import com.move.islam.presentation.view.main.adapter.CarsAdapter
import com.move.islam.presentation.viewmodel.main.MainActions
import com.move.islam.presentation.viewmodel.main.MainStates
import com.move.islam.presentation.viewmodel.main.MainViewModel
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
    }

    private fun loadCarImages() {
        viewModel.dispatch(MainActions.LoadImages(id = "332199935"))
    }

    private fun initRecyclerView() {
        binding?.list?.apply {
            carsAdapter = CarsAdapter()
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = carsAdapter
        }
    }

    private fun showEmptyList(show: Boolean) {
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
            is MainStates.ImageURLList -> {
                if (it.lImages.isEmpty()) {
                    showEmptyList(true)
                    binding?.retryBtn?.gone()
                    binding?.emptyListText?.text = getString(R.string.empty_list)
                } else {
                    showEmptyList(false)
                    carsAdapter.submitList(it.lImages)
                }
            }
            is MainStates.ShowERRORMessage -> {
                showEmptyList(true)
                binding?.emptyListText?.text = it.reason
            }
        }
    }


}