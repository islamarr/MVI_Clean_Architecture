package com.move.mvisample.presentation.viewmodel.main

import com.move.mvisample.domain.entites.CarImage

sealed class MainStates{ //TODO handle loading state
    object Idle: MainStates()
    data class ShowERRORMessage(val reason: String, val errorCode: Long) : MainStates()
    data class ImageURLList(val hImages: List<CarImage>, val lImages: List<CarImage>) : MainStates()
}
