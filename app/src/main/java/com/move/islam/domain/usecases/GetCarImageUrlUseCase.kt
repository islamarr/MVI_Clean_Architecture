package com.move.islam.domain.usecases

import com.move.islam.domain.entites.CarImage
import com.move.islam.domain.repositories.GetCarImagesRepository
import com.move.islam.presentation.viewmodel.main.MainResults
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

private const val TAG = "GetCarImageUrl"

@ViewModelScoped
class GetCarImageUrlUseCase @Inject constructor(private val getCarImagesRepository: GetCarImagesRepository) {

    suspend fun execute(id: String): MainResults {
        val response = getCarImagesRepository.getCars(id)
        return if (!response.isSuccessful)
            MainResults.ERROR(response.message(), response.code().toLong())
        else getImageURL(response.body()!!.carImages)


    }

    private fun getImageURL(carImages: List<CarImage>): MainResults {
        val lImagesUrl = carImages.map {
            CarImage(
                "https://" + it.uri.replace(
                    "m.mobile.de/yams-proxy/",
                    ""
                ) + "?rule=mo-640.jpg"
            )
        }
        val hImagesUrl = carImages.map {
            CarImage(
                "https://" + it.uri.replace(
                    "m.mobile.de/yams-proxy/",
                    ""
                ) + "?rule=mo-1600.jpg"
            )
        }
        return MainResults.ImageURL(hImagesUrl, lImagesUrl)
    }
}