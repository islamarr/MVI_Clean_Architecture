package com.islam.mvisample.domain.usecases

import com.islam.mvisample.common.HTTPS
import com.islam.mvisample.data.remote.NetworkResponse
import com.islam.mvisample.domain.entites.CarImage
import com.islam.mvisample.domain.repositories.GetCarImagesRepository
import com.islam.mvisample.presentation.viewmodel.main.MainResults
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetCarImageUrlUseCase @Inject constructor(private val getCarImagesRepository: GetCarImagesRepository) {

    suspend fun execute(id: String): MainResults {
        return when (val response = getCarImagesRepository.getCars(id)) {
            is NetworkResponse.Failure -> MainResults.ERROR(response.reason!!, response.httpCode)
            is NetworkResponse.Success -> {
                response.data?.body()?.let {
                    if (it.carImages.isEmpty()) MainResults.CarImageURLEmptyList else
                        MainResults.CarImageURLListLoaded(getImagesUrlList(it.carImages))
                } ?: MainResults.UnExpectedError
            }
        }
    }

    private fun getImagesUrlList(carImages: List<CarImage>) = carImages.map {
        CarImage(
            HTTPS + it.uri.replace(
                "m.mobile.de/yams-proxy/",
                ""
            ) + ImageURLQUERY.LOW_QUALITY.query
        )
    }
}

enum class ImageURLQUERY(val query: String) {
    LOW_QUALITY("?rule=mo-640.jpg"),
    HIGH_QUALITY("?rule=mo-1600.jpg")
}