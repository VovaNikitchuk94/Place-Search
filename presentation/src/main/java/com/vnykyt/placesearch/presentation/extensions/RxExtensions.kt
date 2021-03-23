package com.vnykyt.placesearch.presentation.extensions

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

private const val THROTTLING_INTERVAL_MILLIS = 500L

fun <T : Any> Observable<T>.throttleFirst(interval: Long = THROTTLING_INTERVAL_MILLIS): Observable<T> =
    throttleFirst(interval, TimeUnit.MILLISECONDS, Schedulers.computation())

fun <T : Any> Observable<T>.debounce(): Observable<T> =
    debounce(THROTTLING_INTERVAL_MILLIS, TimeUnit.MILLISECONDS, Schedulers.computation())

fun <T : Any> Observable<T>.throttleLast(interval: Long = THROTTLING_INTERVAL_MILLIS): Observable<T> =
    throttleLast(interval, TimeUnit.MILLISECONDS, Schedulers.computation())