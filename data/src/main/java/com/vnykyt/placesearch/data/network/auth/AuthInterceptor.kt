package com.vnykyt.placesearch.data.network.auth

import com.vnykyt.placesearch.config.EnvironmentConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

internal class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return chain.proceed(createSignedRequest(request))
    }

    private fun createSignedRequest(baseRequest: Request): Request = when (AuthorizationType.fromRequest(baseRequest)) {
        AuthorizationType.GOOGLE_MAPS -> {
            val url: HttpUrl = baseRequest.url.newBuilder()
                .addQueryParameter("key", EnvironmentConfig.googleMapsApiKey())
                .build()
            baseRequest.newBuilder().url(url).build()
        }
        AuthorizationType.FOURSQUARE -> {
            val url: HttpUrl = baseRequest.url.newBuilder()
                .addQueryParameter("client_id", EnvironmentConfig.foursquareApiKey())
                .addQueryParameter("client_secret", EnvironmentConfig.foursquareSecret())
                .addQueryParameter("v", "20190425")
                .build()
            baseRequest.newBuilder().url(url).build()
        }
        else -> baseRequest
    }
}