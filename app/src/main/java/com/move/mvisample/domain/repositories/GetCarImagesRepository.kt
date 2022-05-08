package com.move.mvisample.domain.repositories

import com.move.mvisample.data.remote.NetworkResponse
import com.move.mvisample.data.remote.repositories.BaseRepository
import com.move.mvisample.domain.entites.CarResponse
import retrofit2.Response

interface GetCarImagesRepository : BaseRepository {
    suspend fun getCars(id: String): NetworkResponse<Response<CarResponse>>
}