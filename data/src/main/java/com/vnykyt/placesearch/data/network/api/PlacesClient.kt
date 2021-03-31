package com.vnykyt.placesearch.data.network.api

import com.vnykyt.placesearch.data.network.auth.AuthorizationType
import com.vnykyt.placesearch.data.network.model.places.PlacesResponse
import com.vnykyt.placesearch.data.network.model.places.VenueAndGeocodeResponse
import com.vnykyt.placesearch.data.network.model.places.VenueDetailsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Tag

internal interface PlacesClient {

    @GET("venues/search")
    fun getPlaces(
        @Query("query") query: String,
        @Query("near") near: String = "Seattle,+WA",
        @Query("limit") limit: Int = 20,
        @Tag authorization: AuthorizationType = AuthorizationType.FOURSQUARE
    ): Single<PlacesResponse<VenueAndGeocodeResponse>>

    @GET("venues/{id}")
    fun getDetailsOfPlace(
        @Path("id") id: String,
        @Tag authorization: AuthorizationType = AuthorizationType.FOURSQUARE
    ): Single<PlacesResponse<VenueDetailsResponse>>
}