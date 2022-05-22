package com.islam.mvisample.data.repositories

import com.islam.mvisample.data.remote.NetworkResponse
import com.islam.mvisample.data.remote.data_source.GetCarImagesRemoteDataSource
import com.islam.mvisample.domain.entites.CarResponse
import com.islam.mvisample.domain.repositories.GetCarImagesRepository
import retrofit2.Response
import javax.inject.Inject

class GetCarImagesRepositoryImpl @Inject constructor(private val getCarImagesRemoteDataSource: GetCarImagesRemoteDataSource) :
    GetCarImagesRepository {
    override suspend fun getCars(id: String): NetworkResponse<Response<CarResponse>> {
        return getCarImagesRemoteDataSource.getCars(id)
    }
}