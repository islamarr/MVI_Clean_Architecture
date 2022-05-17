package com.islam.mvisample.presentation.viewmodel.main

sealed class MainActions {
    data class LoadImages(val id: String) : MainActions()
}