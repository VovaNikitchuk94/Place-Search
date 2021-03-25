package com.vnykyt.placesearch.data.network.api

import com.vnykyt.placesearch.data.network.model.PlacesResponse
import com.vnykyt.placesearch.data.network.model.VenueAndGeocodeResponse
import com.vnykyt.placesearch.data.network.model.VenueDetailsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface PlacesClient {

    @GET("venues/search")
    fun getPlaces(
        @Query("query") query: String,
        @Query("near") near: String = "Seattle,+WA",
        @Query("limit") limit: Int = 20,
        @Query("client_id") client_id: String = "4WFDGRA3G5BPIN0PBNYQBGEKOKJPKSQH205OL1XJLEGI4UPG",
        @Query("client_secret") client_secret: String = "HDF1YWNZKGQD5WDOVT53B3TIVHZNHSEXP3ISDYRTJZ1UFWR3",
        @Query("v") v: String = "20190425"
    ): Single<PlacesResponse<VenueAndGeocodeResponse>>

    @GET("venues/{id}")
    fun getDetailsOfPlace(
        @Path("id") id: String,
        @Query("client_id") client_id: String = "4WFDGRA3G5BPIN0PBNYQBGEKOKJPKSQH205OL1XJLEGI4UPG",
        @Query("client_secret") client_secret: String = "HDF1YWNZKGQD5WDOVT53B3TIVHZNHSEXP3ISDYRTJZ1UFWR3",
        @Query("v") v: String = "20190425"
    ): Single<PlacesResponse<VenueDetailsResponse>>
}