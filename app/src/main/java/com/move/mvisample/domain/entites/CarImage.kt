package com.move.mvisample.domain.entites

import com.google.gson.annotations.SerializedName

data class CarImage(
    @SerializedName("uri")
    val uri: String
)