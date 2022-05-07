package com.move.islam.presentation.viewmodel.main

import com.move.islam.domain.entites.CarImage

sealed class MainResults {
    data class ImageURL(val hImages: List<CarImage>, val lImages: List<CarImage>) : MainResults()
    data class ERROR(val reason: String, val errorCode: Long) : MainResults()
}
