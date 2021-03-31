package com.vnykyt.placesearch.data.network.api

import com.vnykyt.placesearch.data.network.auth.AuthorizationType
import com.vnykyt.placesearch.data.network.model.maps.DistanceResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Tag

internal interface MapsClient {

    @GET("distancematrix/json")
    fun getDistance(
        @Query("origins") origins: String,
        @Query("destinations") destinations: String,
        @Query("mode") mode: String = "walking",
        @Tag authorization: AuthorizationType = AuthorizationType.GOOGLE_MAPS
    ): Single<DistanceResponse>
}