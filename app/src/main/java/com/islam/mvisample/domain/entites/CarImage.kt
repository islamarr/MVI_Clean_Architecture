package com.islam.mvisample.domain.entites

import com.google.gson.annotations.SerializedName

data class CarImage(
    @SerializedName("uri")
    val uri: String
)