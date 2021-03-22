package com.vnykyt.placesearch.data.repository

import com.vnykyt.placesearch.api.model.place.Places
import com.vnykyt.placesearch.api.repository.PlacesRepository
import com.vnykyt.placesearch.data.mapper.toPlaces
import com.vnykyt.placesearch.data.network.api.PlacesClient
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

internal class DataPlacesRepository(
    private val placesClient: PlacesClient
) : PlacesRepository {

    override fun searchPlace(query: String): Single<Places> =
        placesClient.getPlaces(query = query)
            .map { it.toPlaces() }
            .subscribeOn(Schedulers.io())
}