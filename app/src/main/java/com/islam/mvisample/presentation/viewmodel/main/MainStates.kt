package com.islam.mvisample.presentation.viewmodel.main

import com.islam.mvisample.domain.entites.CarImage

sealed class MainStates {
    object InitialState : MainStates()
    object Loading : MainStates()
    data class ShowErrorMessage(val reason: String? = null, val errorCode: Int? = 0) : MainStates()
    data class CarImagesLoaded(val carImageURLList: List<CarImage>) : MainStates()
    object EmptyCarList : MainStates()
}
