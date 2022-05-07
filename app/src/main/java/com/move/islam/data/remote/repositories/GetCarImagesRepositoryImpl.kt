package com.move.islam.data.remote.repositories

import com.move.islam.data.remote.AppService
import com.move.islam.domain.entites.CarResponse
import com.move.islam.domain.repositories.GetCarImagesRepository
import retrofit2.Response
import javax.inject.Inject

class GetCarImagesRepositoryImpl @Inject constructor(private val appService: AppService) :
    GetCarImagesRepository {
    override suspend fun getCars(id: String): Response<CarResponse> {
        return appService.getCars(id)
    }
}