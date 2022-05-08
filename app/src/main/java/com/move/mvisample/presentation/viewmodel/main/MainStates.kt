package com.move.mvisample.presentation.viewmodel.main

import com.move.mvisample.domain.entites.CarImage

sealed class MainStates {
    object Idle : MainStates()
    object Loading : MainStates()
    data class ShowErrorMessage(val reason: String, val errorCode: Int) : MainStates()
    data class CarImagesLoaded(val carImageURLList: List<CarImage>) : MainStates()
    object EmptyCarList : MainStates()
}
