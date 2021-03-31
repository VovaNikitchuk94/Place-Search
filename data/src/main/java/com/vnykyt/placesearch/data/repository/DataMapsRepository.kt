package com.vnykyt.placesearch.data.repository

import com.vnykyt.placesearch.api.repository.MapsRepository
import com.vnykyt.placesearch.config.EnvironmentConfig
import com.vnykyt.placesearch.data.network.api.MapsClient
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

internal class DataMapsRepository(
    private val mapsClient: MapsClient
) : MapsRepository {

    private companion object {
        private const val CENTER = "47.6062,-122.3321"
        private const val ZOOM = 13
        private const val SIZE = "400x400"
        private const val MAP_TYPE = "roadmap"
    }

    override fun getImageMap(location: String): Single<String> = Single.fromCallable {
        "https://maps.googleapis.com/maps/api/staticmap?" +
            "center=$CENTER" +
            "&zoom=$ZOOM" +
            "&size=$SIZE" +
            "&maptype=$MAP_TYPE" +
            "&markers=color:blue%7Clabel:A%7C$location" +
            "&markers=color:red%7Clabel:B%7C$CENTER" +
            "&key=${EnvironmentConfig.googleMapsApiKey()}"
    }

    override fun getMapDistance(location: String): Single<String> =
        mapsClient.getDistance(origins = CENTER, destinations = location)
            .map { it.rows.first().elements.firstOrNull()?.distance?.text ?: "" }
            .subscribeOn(Schedulers.io())
}