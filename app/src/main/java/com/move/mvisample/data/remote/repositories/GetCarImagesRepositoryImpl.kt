package com.move.mvisample.data.remote.repositories

import com.move.mvisample.data.remote.AppService
import com.move.mvisample.domain.entites.CarResponse
import com.move.mvisample.domain.repositories.GetCarImagesRepository
import retrofit2.Response
import javax.inject.Inject

class GetCarImagesRepositoryImpl @Inject constructor(private val appService: AppService) :
    GetCarImagesRepository {
    override suspend fun getCars(id: String): Response<CarResponse> {
        return appService.getCars(id)
    }
}