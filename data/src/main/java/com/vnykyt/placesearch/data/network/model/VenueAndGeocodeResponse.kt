package com.vnykyt.placesearch.data.network.model

internal data class PlacesResponse(
    val response: VenueAndGeocodeResponse
)

internal data class VenueAndGeocodeResponse(
    val venues: List<VenueResponse>,
    val geocode: GeocodeResponse
)

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

internal data class VenueResponse(
    val categories: List<CategoryResponse>?,
    val delivery: DeliveryResponse?,
    val id: String,
    val location: LocationResponse?,
    val name: String
)

internal data class CategoryResponse(
    val icon: IconResponse?,
    val id: String,
    val name: String?,
    val pluralName: String?,
    val primary: Boolean?,
    val shortName: String?
)

internal data class IconResponse(
    val prefix: String?,
    val suffix: String?
)

internal data class DeliveryResponse(
    val id: String,
    val provider: ProviderResponse?,
    val url: String?
)

internal data class ProviderResponse(
    val icon: IconResponse?,
    val name: String?
)

internal data class LocationResponse(
    val address: String?,
    val cc: String?,
    val city: String?,
    val country: String?,
    val crossStreet: String?,
    val formattedAddress: List<String>?,
    val lat: Double,
    val lng: Double,
    val neighborhood: String?,
    val postalCode: String?,
    val state: String?
)