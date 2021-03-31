package com.vnykyt.placesearch.api.error

import io.reactivex.rxjava3.functions.Consumer

interface ErrorHandler : Consumer<Throwable?> {

    operator fun invoke(throwable: Throwable?) = accept(throwable)

    override fun accept(throwable: Throwable?)
}