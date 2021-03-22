package com.vnykyt.placesearch.data.mapper

import com.vnykyt.placesearch.api.model.place.Category
import com.vnykyt.placesearch.api.model.place.Center
import com.vnykyt.placesearch.api.model.place.Delivery
import com.vnykyt.placesearch.api.model.place.Feature
import com.vnykyt.placesearch.api.model.place.Geocode
import com.vnykyt.placesearch.api.model.place.Geometry
import com.vnykyt.placesearch.api.model.place.Icon
import com.vnykyt.placesearch.api.model.place.Location
import com.vnykyt.placesearch.api.model.place.Places
import com.vnykyt.placesearch.api.model.place.Provider
import com.vnykyt.placesearch.api.model.place.Venue
import com.vnykyt.placesearch.data.network.model.CategoryResponse
import com.vnykyt.placesearch.data.network.model.DeliveryResponse
import com.vnykyt.placesearch.data.network.model.FeatureResponse
import com.vnykyt.placesearch.data.network.model.GeocodeResponse
import com.vnykyt.placesearch.data.network.model.GeometryResponse
import com.vnykyt.placesearch.data.network.model.IconResponse
import com.vnykyt.placesearch.data.network.model.LocationResponse
import com.vnykyt.placesearch.data.network.model.PlacesResponse
import com.vnykyt.placesearch.data.network.model.ProviderResponse
import com.vnykyt.placesearch.data.network.model.VenueResponse

internal fun PlacesResponse.toPlaces() = Places(
    venue = response.venues.map { it.toVenue() },
    geocode = response.geocode.toGeocode()
)

internal fun VenueResponse.toVenue() = Venue(
    id = id,
    name = name,
    categories = categories?.map { it.toCategory() } ?: emptyList(),
    delivery = delivery?.toDelivery() ?: Delivery.EMPTY,
    location = location?.toLocation() ?: Location.EMPTY
)

internal fun CategoryResponse.toCategory() = Category(
    id = id ?: "",
    name = name ?: "",
    pluralName = pluralName ?: "",
    primary = primary ?: false,
    shortName = shortName ?: "",
    icon = icon?.toIcon() ?: Icon.EMPTY
)

internal fun IconResponse.toIcon() = Icon(
    prefix = prefix ?: "",
    suffix = suffix ?: ""
)

internal fun DeliveryResponse.toDelivery() = Delivery(
    id = id,
    url = url ?: "",
    provider = provider?.toProvider() ?: Provider.EMPTY
)

internal fun ProviderResponse.toProvider() = Provider(
    icon = icon?.toIcon() ?: Icon.EMPTY,
    name = name ?: ""
)

internal fun LocationResponse.toLocation() = Location(
    address = address ?: "",
    cc = cc ?: "",
    city = city ?: "",
    country = country ?: "",
    crossStreet = crossStreet ?: "",
    formattedAddress = formattedAddress ?: emptyList(),
    lat = lat,
    lng = lng,
    neighborhood = neighborhood ?: "",
    postalCode = postalCode ?: "",
    state = state ?: "",
)

internal fun GeocodeResponse.toGeocode() = Geocode(
    feature = feature.toFeature()
)

internal fun FeatureResponse.toFeature() = Feature(
    cc = cc ?: "",
    displayName = displayName ?: "",
    name = name ?: "",
    matchedName = matchedName ?: "",
    geometry = geometry?.toGeometry() ?: Geometry(Center(0.0, 0.0))
)

internal fun GeometryResponse.toGeometry() = Geometry(
    Center(
        lat = center.lat,
        lng = center.lng
    )
)