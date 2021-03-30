package com.vnykyt.placesearch.api.error

data class ReadableException(
    override val message: String,
    override val cause: Throwable? = null
) : AppException()