package com.vnykyt.placesearch.presentation.base

import com.vnykyt.placesearch.api.error.ErrorHandler
import com.vnykyt.placesearch.presentation.BuildConfig
import timber.log.Timber

class AppErrorHandler : ErrorHandler {

    private var handledException: Throwable? = null

    override fun accept(throwable: Throwable?) {
        throwable?.takeIf { it != handledException }?.let {
            Timber.w(it, "Error was handled")
            when (it) {
                else -> if (BuildConfig.DEBUG) throw it
            }
            handledException = it
        }
    }
}