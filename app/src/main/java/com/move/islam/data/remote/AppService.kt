package com.move.islam.data.remote

import com.move.islam.domain.entites.CarResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AppService {

    @GET("svc/a/{id}")
    suspend fun getCars(@Path("id") id: String): NetworkResponse<BaseResponse<CarResponse>>
}