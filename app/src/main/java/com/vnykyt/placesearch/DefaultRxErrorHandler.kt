package com.vnykyt.placesearch

import io.reactivex.rxjava3.exceptions.UndeliverableException
import io.reactivex.rxjava3.functions.Consumer
import timber.log.Timber
import java.io.IOException
import java.net.SocketException

class DefaultRxErrorHandler : Consumer<Throwable> {

    override fun accept(e: Throwable?) {
        when (
            val exception = (e as? UndeliverableException)?.cause ?: e) {
            is IOException, is SocketException -> {
                // fine, irrelevant network problem or API that throws on cancellation}
            }
            is InterruptedException -> {
                // fine, some blocking code was interrupted by a dispose call
            }
            is NullPointerException, is IllegalArgumentException -> {
                // that's likely a bug in the application
                Thread.currentThread().uncaughtExceptionHandler
                    ?.uncaughtException(Thread.currentThread(), exception)
            }
            is IllegalStateException -> {
                // that's a bug in RxJava or in a custom operator
                Thread.currentThread().uncaughtExceptionHandler
                    ?.uncaughtException(Thread.currentThread(), exception)
            }
            else -> Timber.w(exception, "Undeliverable throwable received, not sure what to do")
        }
    }
}