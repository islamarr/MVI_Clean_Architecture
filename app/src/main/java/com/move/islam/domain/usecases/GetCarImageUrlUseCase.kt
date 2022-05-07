package com.move.islam.domain.usecases

import com.move.islam.data.remote.NetworkResponse
import com.move.islam.domain.entites.Image
import com.move.islam.domain.repositories.GetCarImagesRepository
import com.move.islam.presentation.viewmodel.main.MainResults
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

private const val TAG = "GetCarImageUrl"

@ViewModelScoped
class GetCarImageUrlUseCase @Inject constructor(private val getCarImagesRepository: GetCarImagesRepository) {

    suspend fun execute(id: String): MainResults {
        return when (val response = getCarImagesRepository.getCars(id)) {
            is NetworkResponse.Failure -> MainResults.ERROR(response.reason!!, response.httpCode)
            is NetworkResponse.Success -> getImageURL(response.data!!.data!!.images)
        }

    }

    private fun getImageURL(images: List<Image>): MainResults {
        val lImagesUrl = images.map {
            "https://" + it.uri.replace("m.mobile.de/yams-proxy/", "") + "?rule=mo-640.jpg"
        }
        val hImagesUrl = images.map {
            "https://" + it.uri.replace("m.mobile.de/yams-proxy/", "") + "?rule=mo-1600.jpg"
        }
        return MainResults.ImageURL(hImagesUrl, lImagesUrl)
    }
}