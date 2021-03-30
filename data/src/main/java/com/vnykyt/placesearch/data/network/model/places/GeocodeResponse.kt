package com.vnykyt.placesearch.data.network.model.places

internal data class GeocodeResponse(
    val feature: FeatureResponse
)

internal data class FeatureResponse(
    val cc: String?,
    val displayName: String?,
    val geometry: GeometryResponse?,
    val matchedName: String?,
    val name: String?
)

internal data class GeometryResponse(
    val center: CenterResponse
)

internal data class CenterResponse(
    val lat: Double,
    val lng: Double
)