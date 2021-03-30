package com.vnykyt.placesearch.data.mapper

import com.vnykyt.placesearch.api.model.place.Attributes
import com.vnykyt.placesearch.api.model.place.Category
import com.vnykyt.placesearch.api.model.place.Center
import com.vnykyt.placesearch.api.model.place.Contact
import com.vnykyt.placesearch.api.model.place.Delivery
import com.vnykyt.placesearch.api.model.place.Feature
import com.vnykyt.placesearch.api.model.place.Geocode
import com.vnykyt.placesearch.api.model.place.Geometry
import com.vnykyt.placesearch.api.model.place.Group
import com.vnykyt.placesearch.api.model.place.GroupType
import com.vnykyt.placesearch.api.model.place.Hours
import com.vnykyt.placesearch.api.model.place.Icon
import com.vnykyt.placesearch.api.model.place.Location
import com.vnykyt.placesearch.api.model.place.Open
import com.vnykyt.placesearch.api.model.place.Photo
import com.vnykyt.placesearch.api.model.place.Price
import com.vnykyt.placesearch.api.model.place.Provider
import com.vnykyt.placesearch.api.model.place.TimeFrame
import com.vnykyt.placesearch.api.model.place.Venue
import com.vnykyt.placesearch.api.model.place.VenuesAndGeocode
import com.vnykyt.placesearch.data.network.model.places.AttributesResponse
import com.vnykyt.placesearch.data.network.model.places.CategoryResponse
import com.vnykyt.placesearch.data.network.model.places.ContactResponse
import com.vnykyt.placesearch.data.network.model.places.DeliveryResponse
import com.vnykyt.placesearch.data.network.model.places.FeatureResponse
import com.vnykyt.placesearch.data.network.model.places.GeocodeResponse
import com.vnykyt.placesearch.data.network.model.places.GeometryResponse
import com.vnykyt.placesearch.data.network.model.places.GroupResponse
import com.vnykyt.placesearch.data.network.model.places.HoursResponse
import com.vnykyt.placesearch.data.network.model.places.IconResponse
import com.vnykyt.placesearch.data.network.model.places.LocationResponse
import com.vnykyt.placesearch.data.network.model.places.OpenResponse
import com.vnykyt.placesearch.data.network.model.places.PhotoResponse
import com.vnykyt.placesearch.data.network.model.places.PlacesResponse
import com.vnykyt.placesearch.data.network.model.places.PriceResponse
import com.vnykyt.placesearch.data.network.model.places.ProviderResponse
import com.vnykyt.placesearch.data.network.model.places.TimeframeResponse
import com.vnykyt.placesearch.data.network.model.places.VenueAndGeocodeResponse
import com.vnykyt.placesearch.data.network.model.places.VenueResponse

internal fun PlacesResponse<VenueAndGeocodeResponse>.toVenuesAndGeocode() = VenuesAndGeocode(
    venue = response.venues.map { it.toVenue() },
    geocode = response.geocode.toGeocode()
)

internal fun VenueResponse.toVenue() = Venue(
    id = id,
    name = name,
    categories = categories?.map { it.toCategory() } ?: emptyList(),
    delivery = delivery?.toDelivery() ?: Delivery.EMPTY,
    location = location?.toLocation() ?: Location.EMPTY,
    bestPhoto = bestPhoto?.toPhoto() ?: Photo.EMPTY,
    contact = contact?.toContact() ?: Contact.EMPTY,
    defaultHours = defaultHours?.toHours() ?: Hours.EMPTY,
    hours = hours?.toHours() ?: Hours.EMPTY,
    rating = rating ?: 0.0,
    ratingColor = ratingColor ?: "",
    url = url ?: "",
    distance = "",
    attributes = attributes?.toAttributes() ?: Attributes(emptyList()),
    price = price?.toPrice() ?: Price.EMPTY
)

internal fun AttributesResponse.toAttributes() = Attributes(
    groups = groups?.map { it.toGroup() } ?: emptyList()
)

internal fun GroupResponse.toGroup() = Group(
    type = GroupType.fromValue(type),
    name = name ?: "",
    summary = summary ?: ""
)

internal fun PriceResponse.toPrice() = Price(
    tier = tier ?: 0,
    message = message ?: "",
    currency = currency ?: ""
)

internal fun PhotoResponse.toPhoto() = Photo(
    id = id,
    height = height,
    width = width,
    prefix = prefix,
    suffix = suffix
)

internal fun ContactResponse.toContact() = Contact(
    formattedPhone = formattedPhone ?: "",
    phone = phone ?: "",
    twitter = twitter ?: ""
)

internal fun HoursResponse.toHours() = Hours(
    isLocalHoliday = isLocalHoliday ?: false,
    isOpen = isOpen ?: false,
    status = status ?: "",
    timeFrames = timeframes?.map { it.toTimeFrame() } ?: emptyList()
)

internal fun TimeframeResponse.toTimeFrame() = TimeFrame(
    days = days ?: "",
    includesToday = includesToday ?: false,
    open = open?.map { it.toRenderedTime() } ?: emptyList()
)

internal fun OpenResponse.toRenderedTime() = Open(
    renderedTime = renderedTime ?: ""
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