package com.islam.mvisample.data.remote.data_source

import com.islam.mvisample.data.remote.api.ApiService
import com.islam.mvisample.data.remote.NetworkResponse
import com.islam.mvisample.domain.entites.CarResponse
import retrofit2.Response
import javax.inject.Inject

class GetCarImagesRemoteDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    GetCarImagesRemoteDataSource {
    override suspend fun getCars(id: String): NetworkResponse<Response<CarResponse>> {
        return safeApiCall {
            apiService.getCars(id)
        }
    }
}