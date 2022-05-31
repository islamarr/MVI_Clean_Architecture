package com.islam.mvisample.data.repositories

import com.islam.mvisample.data.remote.NetworkResponse
import com.islam.mvisample.data.remote.data_source.CarImagesRemoteDataSource
import com.islam.mvisample.domain.entites.CarResponse
import com.islam.mvisample.domain.repositories.CarImagesRepository
import retrofit2.Response
import javax.inject.Inject

class CarImagesRepositoryImpl @Inject constructor(private val carImagesRemoteDataSource: CarImagesRemoteDataSource) :
    CarImagesRepository {
    override suspend fun getCars(id: String): NetworkResponse<Response<CarResponse>> {
        return carImagesRemoteDataSource.getCars(id)
    }
}