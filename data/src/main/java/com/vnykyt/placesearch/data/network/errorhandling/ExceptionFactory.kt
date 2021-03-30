package com.vnykyt.placesearch.data.network.errorhandling

import com.vnykyt.placesearch.api.error.ConnectivityException
import com.vnykyt.placesearch.api.error.ReadableException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

internal class ExceptionFactory {

    fun create(throwable: Throwable): Throwable = when {
        throwable.isConnectivityException() -> ConnectivityException(throwable.localizedMessage.orEmpty(), throwable)
        throwable is HttpException -> ReadableException(throwable.localizedMessage.orEmpty(), throwable)
        else -> throwable
    }

    private fun Throwable.isConnectivityException() = this is ConnectException ||
        this is UnknownHostException ||
        this is SocketTimeoutException ||
        this is SocketException
}