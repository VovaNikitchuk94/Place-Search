package com.vnykyt.placesearch.api.model.place

data class Location(
    val address: String,
    val cc: String,
    val city: String,
    val country: String,
    val crossStreet: String,
    val formattedAddress: List<String>,
    val lat: Double,
    val lng: Double,
    val neighborhood: String,
    val postalCode: String,
    val state: String
) {

    companion object {

        val EMPTY = Location(
            address = "",
            cc = "",
            city = "",
            country = "",
            crossStreet = "",
            formattedAddress = emptyList(),
            lat = 0.0,
            lng = 0.0,
            neighborhood = "",
            postalCode = "",
            state = ""
        )
    }

    val coordinates: String
        get() = "$lat,$lng"
}