package com.islam.mvisample.domain.repositories

import com.islam.mvisample.data.remote.NetworkResponse
import com.islam.mvisample.data.remote.repositories.BaseRepository
import com.islam.mvisample.domain.entites.CarResponse
import retrofit2.Response

interface GetCarImagesRepository : BaseRepository {
    suspend fun getCars(id: String): NetworkResponse<Response<CarResponse>>
}