package com.move.islam.presentation.view.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.move.islam.domain.usecases.GetCarImageUrlUseCase
import com.move.islam.presentation.viewmodel.main.MainActions
import com.move.islam.presentation.viewmodel.main.MainResults
import com.move.islam.presentation.viewmodel.main.MainStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getCarImage: GetCarImageUrlUseCase) :
    ViewModel() {
    private val _state = MutableStateFlow<MainStates>(MainStates.Idle)
    val state: StateFlow<MainStates>
        get() = _state.asStateFlow()


    fun dispatch(action: MainActions) {
        handle(action).map { result ->
            reduce(result)
        }.onEach { state ->
            onViewState(state)
        }.launchIn(viewModelScope)
    }

    suspend fun onViewState(state: MainStates) {
        _state.emit(state)
    }

    fun reduce(result: MainResults): MainStates =
        when (result) {
            is MainResults.ERROR -> MainStates.ShowERRORMessage(result.reason, result.errorCode)
            is MainResults.ImageURL -> MainStates.ImageURLList(result.hImages, result.lImages)
        }

    fun handle(actions: MainActions): Flow<MainResults> = flow {
        when (actions) {
            is MainActions.LoadImages -> emit(getCarImage.execute("332199935"))
        }
    }


}