package com.move.mvisample.domain.usecases

import com.move.mvisample.domain.entites.CarImage
import com.move.mvisample.domain.repositories.GetCarImagesRepository
import com.move.mvisample.presentation.viewmodel.main.MainResults
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

private const val TAG = "GetCarImageUrl"

@ViewModelScoped
class GetCarImageUrlUseCase @Inject constructor(private val getCarImagesRepository: GetCarImagesRepository) {

    suspend fun execute(id: String): MainResults {
        val response = getCarImagesRepository.getCars(id)
        return if (response.isSuccessful)
            getImageURL(response.body()!!.carImages)
        else MainResults.ERROR(response.message(), response.code().toLong())
    }

    private fun getImageURL(carImages: List<CarImage>): MainResults {//TODO make sure it is the best approach
        val lImagesUrl = carImages.map {
            CarImage(
                "https://" + it.uri.replace(
                    "m.mobile.de/yams-proxy/",
                    ""
                ) + "?rule=mo-640.jpg"
            )
        }
        return MainResults.ImageURL(lImagesUrl)
    }
}