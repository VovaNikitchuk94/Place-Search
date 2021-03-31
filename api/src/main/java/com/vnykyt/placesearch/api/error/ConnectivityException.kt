package com.vnykyt.placesearch.api.error

class ConnectivityException(
    message: String,
    cause: Throwable? = null
) : AppException(message, cause)