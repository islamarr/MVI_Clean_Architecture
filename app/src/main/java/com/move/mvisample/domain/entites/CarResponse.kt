package com.move.mvisample.domain.entites

import com.google.gson.annotations.SerializedName

data class CarResponse(
    @SerializedName("images")
    val carImages: List<CarImage>,
)