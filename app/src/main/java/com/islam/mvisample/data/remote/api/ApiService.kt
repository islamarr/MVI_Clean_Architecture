package com.islam.mvisample.data.remote.api

import com.islam.mvisample.domain.entites.CarResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/svc/a/{id}")
    suspend fun getCars(@Path("id") id: String): Response<CarResponse> //TODO remove retrofit response
}