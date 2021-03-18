package com.vnykyt.placesearch.api.location

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

interface LocationProvider {

    fun getCurrentLocation(): Maybe<Unit> // LatLng

    fun isLocationEnabled(): Single<Boolean>

    fun isLocationPermissionGranted(): Single<Boolean>
}