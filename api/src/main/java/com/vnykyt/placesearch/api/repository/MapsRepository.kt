package com.vnykyt.placesearch.api.repository

import io.reactivex.rxjava3.core.Single

interface MapsRepository {

    fun getMapDistance(location: String): Single<String>

    fun getImageMap(location: String): Single<String>
}