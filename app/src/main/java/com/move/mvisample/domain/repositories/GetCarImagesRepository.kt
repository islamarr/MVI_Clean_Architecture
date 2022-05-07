package com.move.mvisample.domain.repositories

import com.move.mvisample.domain.entites.CarResponse
import retrofit2.Response

interface GetCarImagesRepository {
    suspend fun getCars(id: String): Response<CarResponse>
}