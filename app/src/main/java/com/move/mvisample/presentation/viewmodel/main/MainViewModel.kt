package com.move.mvisample.presentation.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.move.mvisample.domain.usecases.GetCarImageUrlUseCase
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

    private suspend fun onViewState(state: MainStates) {
        _state.emit(state)
    }

    fun reduce(result: MainResults): MainStates =
        when (result) {
            is MainResults.UnExpectedError -> MainStates.Idle
            is MainResults.ERROR -> MainStates.ShowErrorMessage(result.reason, result.errorCode)
            is MainResults.CarImageURLListLoaded -> MainStates.CarImagesLoaded(result.carImageURLList)
            is MainResults.CarImageURLEmptyList -> MainStates.EmptyCarList
        }

    fun handle(actions: MainActions): Flow<MainResults> = flow {
        when (actions) {
            is MainActions.LoadImages -> emit(getCarImage.execute(actions.id))
        }
    }

}