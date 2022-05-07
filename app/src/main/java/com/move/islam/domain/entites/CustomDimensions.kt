package com.move.islam.domain.entites


import kotlinx.serialization.SerialName


data class CustomDimensions(
    @SerialName("10")
    val x10: String, // comfort
    @SerialName("126")
    val x126: String, // NA_NA_20_name_photo_languages_showroom
    @SerialName("47")
    val x47: String // NA_NA_NA_NA_NA_NA
)