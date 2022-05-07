package com.move.islam.data.remote.repositories

import com.move.islam.data.remote.AppService
import com.move.islam.data.remote.BaseResponse
import com.move.islam.data.remote.NetworkResponse
import com.move.islam.domain.entites.CarResponse
import com.move.islam.domain.repositories.GetCarImagesRepository
import javax.inject.Inject

class GetCarImagesRepositoryImpl @Inject constructor(private val appService: AppService) :
    GetCarImagesRepository {
    override suspend fun getCars(id: String): NetworkResponse<BaseResponse<CarResponse>> {
        return appService.getCars(id)
    }
}