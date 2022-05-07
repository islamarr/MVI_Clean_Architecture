package com.move.islam.domain.repositories

import com.move.islam.data.remote.BaseResponse
import com.move.islam.data.remote.NetworkResponse
import com.move.islam.domain.entites.CarResponse
import retrofit2.Response

interface GetCarImagesRepository {
    suspend fun getCars(id: String): Response<CarResponse>
}