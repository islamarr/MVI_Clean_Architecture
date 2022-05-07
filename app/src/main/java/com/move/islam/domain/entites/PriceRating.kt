package com.move.islam.domain.entites


data class PriceRating(
    val noRatingReason: String, // mobile.de only rates the price of a vehicle if a sufficient volume of comparative data is available. If not, because too little comparable data has been advertised in the past, mobile.de is unable to make a realistic price rating.
    val rating: String, // NO_RATING
    val ratingLabel: String // No rating
)