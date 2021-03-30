package com.vnykyt.placesearch.api.model.place

data class VenuesAndGeocode(
    val venue: List<Venue>,
    val geocode: Geocode
)
