package com.move.mvisample.data.remote

sealed class MainResponse {
   // data class Success(val carResponse: CarResponse): MainResponse()
    data class Failure(val reason: String, val errorCode: Long): MainResponse()
}