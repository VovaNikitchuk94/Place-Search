package com.vnykyt.placesearch.api.model.place

data class Geocode(
    val feature: Feature
)

data class Feature(
    val cc: String,
    val displayName: String,
    val geometry: Geometry,
    val matchedName: String,
    val name: String
)

data class Geometry(
    val center: Center
)

data class Center(
    val lat: Double,
    val lng: Double
)
