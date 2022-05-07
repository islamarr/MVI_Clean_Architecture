package com.move.mvisample.presentation.viewmodel.main

import com.move.mvisample.domain.entites.CarImage

sealed class MainResults {
    object EmptyList: MainResults()
    data class ImageURL(val carImageURLList: List<CarImage>) : MainResults()
    data class ERROR(val reason: String, val errorCode: Long) : MainResults()
}
