package com.vnykyt.placesearch.data.repository

import com.vnykyt.placesearch.api.model.place.Venue
import com.vnykyt.placesearch.api.model.place.VenuesAndGeocode
import com.vnykyt.placesearch.api.repository.PlacesRepository
import com.vnykyt.placesearch.data.mapper.toVenue
import com.vnykyt.placesearch.data.mapper.toVenuesAndGeocode
import com.vnykyt.placesearch.data.network.api.PlacesClient
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

internal class DataPlacesRepository(
    private val placesClient: PlacesClient
) : PlacesRepository {

    override fun searchPlace(query: String): Single<VenuesAndGeocode> =
        placesClient.getPlaces(query = query)
            .map { it.toVenuesAndGeocode() }
            .subscribeOn(Schedulers.io())

    override fun placeDetails(id: String): Single<Venue> =
        placesClient.getDetailsOfPlace(id = id)
            .map { it.response.venue.toVenue() }
            .subscribeOn(Schedulers.io())
}