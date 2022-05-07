package com.move.islam.presentation.viewmodel.main

sealed class MainStates{
    object Idle: MainStates()
    data class ShowERRORMessage(val reason: String, val errorCode: Long) : MainStates()
    data class ImageURLList(val hImages: List<String>, val lImages: List<String>) : MainStates()
}
