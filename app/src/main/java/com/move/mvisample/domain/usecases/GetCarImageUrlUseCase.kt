package com.move.mvisample.domain.usecases

import com.move.mvisample.core.HTTPS
import com.move.mvisample.domain.entites.CarImage
import com.move.mvisample.domain.repositories.GetCarImagesRepository
import com.move.mvisample.presentation.viewmodel.main.MainResults
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetCarImageUrlUseCase @Inject constructor(private val getCarImagesRepository: GetCarImagesRepository) {

    suspend fun execute(id: String): MainResults { //TODO check coverage
        val response = getCarImagesRepository.getCars(id)
        return when {
            response.isSuccessful -> response.body()?.let {
                if (it.carImages.isEmpty()) MainResults.CarImageURLEmptyList else
                    getImageURL(it.carImages)
            } ?: MainResults.UnExpectedError
            else -> MainResults.ERROR(response.message(), response.code().toLong())
        }
    }

    private fun getImageURL(carImages: List<CarImage>): MainResults {
        val lImagesUrl = carImages.map {
            CarImage(
                HTTPS + it.uri.replace(
                    "m.mobile.de/yams-proxy/",
                    ""
                ) + ImageURLQUERY.LOW_QUALITY.query
            )
        }
        return MainResults.CarImageURLListLoaded(lImagesUrl)
    }
}

enum class ImageURLQUERY(val query: String) {
    LOW_QUALITY("?rule=mo-640.jpg"),
    HIGH_QUALITY("?rule=mo-1600.jpg")
}