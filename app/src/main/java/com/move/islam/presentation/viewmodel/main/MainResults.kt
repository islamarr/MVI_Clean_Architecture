package com.move.islam.presentation.viewmodel.main

sealed class MainResults {
    data class ImageURL(val hImages: List<String>, val lImages: List<String>) : MainResults()
    data class ERROR(val reason: String, val errorCode: Long) : MainResults()
}
