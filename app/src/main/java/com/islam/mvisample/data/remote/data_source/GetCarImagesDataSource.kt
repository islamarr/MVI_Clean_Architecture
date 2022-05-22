package com.islam.mvisample.data.remote.data_source

import com.islam.mvisample.data.remote.NetworkResponse
import com.islam.mvisample.data.remote.NetworkRemoteServiceCall
import com.islam.mvisample.domain.entites.CarResponse
import retrofit2.Response

interface GetCarImagesDataSource : NetworkRemoteServiceCall {
    suspend fun getCars(id: String): NetworkResponse<Response<CarResponse>>
}