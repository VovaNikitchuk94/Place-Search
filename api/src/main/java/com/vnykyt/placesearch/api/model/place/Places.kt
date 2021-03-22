package com.vnykyt.placesearch.api.model.place

data class Places(
    val venue: List<Venue>,
    val geocode: Geocode
)
