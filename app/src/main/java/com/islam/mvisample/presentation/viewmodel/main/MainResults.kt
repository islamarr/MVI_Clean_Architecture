package com.islam.mvisample.presentation.viewmodel.main

import com.islam.mvisample.domain.entites.CarImage

sealed class MainResults {
    object UnExpectedError : MainResults()
    object CarImageURLEmptyList : MainResults()
    data class CarImageURLListLoaded(val carImageURLList: List<CarImage>) : MainResults()
    data class Error(val reason: String, val errorCode: Int) : MainResults()
}
