package com.islam.mvisample.domain.entites

import com.squareup.moshi.Json

data class CarImage(
    @field:Json(name = "uri")
    val uri: String
)