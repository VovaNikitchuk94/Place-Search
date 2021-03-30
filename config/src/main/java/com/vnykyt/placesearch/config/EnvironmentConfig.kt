package com.vnykyt.placesearch.config

object EnvironmentConfig {

    fun foursquareBaseUrl(): String = BuildConfig.FOURSQUARE_BASE_URL

    fun foursquareApiKey(): String = BuildConfig.FOURSQUARE_CLIENT_ID

    fun foursquareSecret(): String = BuildConfig.FOURSQUARE_SECRET

    fun googleMapsApiKey(): String = BuildConfig.GOOGLE_MAPS_API_KEY

    fun googleMapsBaseUrl(): String = BuildConfig.GOOGLE_MAPS_BASE_URL
}