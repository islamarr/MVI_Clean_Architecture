package com.move.islam.domain.entites


data class Contact(
    val address1: String, // Steenweg op Deinze 51C
    val address2: String, // BE-9880 Aalter
    val canSendCcMail: Boolean, // true
    val country: String, // BE
    val enumType: String, // DEALER
    val homepageUrl: String, // https://home.mobile.de/home/redirect.html?customerId=472553
    val languages: String, // Deutsch, Français, English, Italiano, Română, Polski
    val latLong: LatLong,
    val messengers: List<Messenger>,
    val name: String, // Oldtimerfarm
    val openingHours: List<OpeningHour>,
    val person: Person,
    val phones: List<Phone>,
    val rating: Rating,
    val showroomGallery: List<ShowroomGallery>,
    val type: String, // Dealer
    val withMobileSince: String // With mobile.de since 19 Apr 2006
)