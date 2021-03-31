package com.vnykyt.placesearch.data.network.auth

import okhttp3.Request

enum class AuthorizationType {
    GOOGLE_MAPS,
    FOURSQUARE,
    NONE;

    companion object {
        fun fromRequest(request: Request): AuthorizationType = request.tag(AuthorizationType::class.java) ?: NONE
    }
}