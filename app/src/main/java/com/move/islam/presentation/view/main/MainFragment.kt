package com.move.islam.presentation.view.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.move.islam.databinding.MainFragmentBinding
import com.move.islam.presentation.view.base.BaseFragment
import com.move.islam.presentation.viewmodel.main.MainActions
import com.move.islam.presentation.viewmodel.main.MainStates
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val TAG = "MainFragment"
@AndroidEntryPoint
class MainFragment : BaseFragment<MainFragmentBinding>() {

    private val viewModel: MainViewModel by viewModels()
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> MainFragmentBinding
        get() = MainFragmentBinding::inflate

    override fun setupOnViewCreated(view: View) {

      //  initRecyclerView()
        startObserver()

        viewModel.dispatch(MainActions.LoadImages)
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
            is MainStates.ImageURLList -> Log.d(TAG, "ImageURLList: ${it.hImages[0]}")
            is MainStates.ShowERRORMessage -> Log.d(TAG, "ShowERRORMessage: ${it.errorCode}")
        }
    }


}