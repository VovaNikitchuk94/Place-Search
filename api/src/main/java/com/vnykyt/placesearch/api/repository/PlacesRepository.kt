package com.vnykyt.placesearch.api.repository

import com.vnykyt.placesearch.api.model.place.Places
import io.reactivex.rxjava3.core.Single

interface PlacesRepository {

    fun searchPlace(query: String): Single<Places>
}