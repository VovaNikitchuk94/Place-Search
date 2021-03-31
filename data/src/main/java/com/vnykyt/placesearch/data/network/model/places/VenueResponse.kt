package com.vnykyt.placesearch.data.network.model.places

internal data class VenueResponse(
    val id: String,
    val name: String,
    val categories: List<CategoryResponse>?,
    val delivery: DeliveryResponse?,
    val location: LocationResponse?,
    val bestPhoto: PhotoResponse?,
    val contact: ContactResponse?,
    val defaultHours: HoursResponse?,
    val hours: HoursResponse?,
    val rating: Double?,
    val ratingColor: String?,
    val url: String?,
    val attributes: AttributesResponse?,
    val price: PriceResponse?
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

internal data class PhotoResponse(
    val id: String,
    val height: Int,
    val width: Int,
    val prefix: String,
    val suffix: String
)

internal data class ContactResponse(
    val formattedPhone: String?,
    val phone: String?,
    val twitter: String?
)

internal data class HoursResponse(
    val isLocalHoliday: Boolean?,
    val isOpen: Boolean?,
    val status: String?,
    val timeframes: List<TimeframeResponse>?
)

internal data class TimeframeResponse(
    val days: String?,
    val includesToday: Boolean?,
    val open: List<OpenResponse>?
)

internal data class OpenResponse(
    val renderedTime: String?
)