package com.islam.mvisample.domain.entites

import com.squareup.moshi.Json

data class CarResponse(
    @field:Json(name = "images")
    val carImages: List<CarImage>,
)