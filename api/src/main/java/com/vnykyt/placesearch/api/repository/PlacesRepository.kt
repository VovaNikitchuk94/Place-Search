package com.vnykyt.placesearch.api.repository

import com.vnykyt.placesearch.api.model.place.VenuesAndGeocode
import com.vnykyt.placesearch.api.model.place.Venue
import io.reactivex.rxjava3.core.Single

interface PlacesRepository {

    fun searchPlace(query: String): Single<VenuesAndGeocode>

    fun placeDetails(id: String): Single<Venue>
}