package com.vnykyt.placesearch.data.network.api

import com.vnykyt.placesearch.data.network.model.maps.DistanceResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

internal interface MapsClient {

    @GET("distancematrix/json")
    fun getDistance(
        @Query("origins") origins: String,
        @Query("destinations") destinations: String,
        @Query("mode") mode: String = "walking",
        @Query("key") client_id: String = "AIzaSyBsxCxoDEPykPuPn84H335z3aGNKat-54k"
    ): Single<DistanceResponse>
}