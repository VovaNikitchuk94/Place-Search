package com.vnykyt.placesearch.data.network.errorhandling

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import java.lang.reflect.Type

internal class RxErrorHandlingCallAdapterFactory(
    private val exceptionFactory: ExceptionFactory
) : CallAdapter.Factory() {

    private val adapterFactory = RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io())

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *> {
        val callAdapter = adapterFactory.get(returnType, annotations, retrofit)
        return CallAdapterWrapper(callAdapter as CallAdapter<*, *>)
    }

    private inner class CallAdapterWrapper<R, T>(private val callAdapter: CallAdapter<R, T>) : CallAdapter<R, T> {

        override fun responseType(): Type = callAdapter.responseType()

        @Suppress("UNCHECKED_CAST")
        override fun adapt(call: Call<R>): T {
            return when (val adapt = callAdapter.adapt(call)) {
                is Completable -> (adapt as Completable).onErrorResumeNext { throwable ->
                    Completable.error(convertException(throwable))
                } as T
                is Single<*> -> (adapt as Single<*>).onErrorResumeNext { throwable ->
                    Single.error(convertException(throwable))
                } as T
                is Maybe<*> -> (adapt as Maybe<*>).onErrorResumeNext { throwable: Throwable ->
                    Maybe.error(convertException(throwable))
                } as T
                is Observable<*> -> (adapt as Observable<*>).onErrorResumeNext { throwable: Throwable ->
                    Observable.error(convertException(throwable))
                } as T
                is Flowable<*> -> (adapt as Flowable<*>).onErrorResumeNext { throwable: Throwable ->
                    Flowable.error(convertException(throwable))
                } as T
                else -> adapt
            }
        }
    }

    private fun convertException(throwable: Throwable) = exceptionFactory.create(throwable)
}