package com.vnykyt.placesearch.data.network.model.places

internal data class VenueAndGeocodeResponse(
    val venues: List<VenueResponse>,
    val geocode: GeocodeResponse
)