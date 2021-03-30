package com.vnykyt.placesearch.api.error

abstract class AppException(
    private val readableMessage: String = "",
    override val cause: Throwable? = null
) : RuntimeException() {

    override val message: String get() = readableMessage.takeIf { it.isNotEmpty() } ?: "Empty message"
}