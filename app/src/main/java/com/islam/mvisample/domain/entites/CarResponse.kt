package com.islam.mvisample.domain.entites

import com.google.gson.annotations.SerializedName

data class CarResponse(
    @SerializedName("images")
    val carImages: List<CarImage>,
)