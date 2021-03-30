package com.vnykyt.placesearch.data.di

import android.location.Geocoder
import android.preference.PreferenceManager
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.vnykyt.placesearch.api.repository.MapsRepository
import com.vnykyt.placesearch.api.repository.PlacesRepository
import com.vnykyt.placesearch.config.EnvironmentConfig
import com.vnykyt.placesearch.data.network.api.MapsClient
import com.vnykyt.placesearch.data.network.api.PlacesClient
import com.vnykyt.placesearch.data.network.errorhandling.ExceptionFactory
import com.vnykyt.placesearch.data.network.errorhandling.RxErrorHandlingCallAdapterFactory
import com.vnykyt.placesearch.data.repository.DataMapsRepository
import com.vnykyt.placesearch.data.repository.DataPlacesRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale
import java.util.concurrent.TimeUnit

@Suppress("LongMethod")
object DataModule {

    private const val QUALIFIER_LOGGING_INTERCEPTOR = "QUALIFIER_LOGGING_INTERCEPTOR"
    private const val QUALIFIER_GSON_CONVERTER_FACTORY = "QUALIFIER_GSON_CONVERTER_FACTORY"
    private const val QUALIFIER_OKHTTP = "QUALIFIER_OKHTTP"
    private const val QUALIFIER_PLACES_RETROFIT = "QUALIFIER_PLACES_RETROFIT"
    private const val QUALIFIER_MAPS_RETROFIT = "QUALIFIER_MAPS_RETROFIT"

    private const val TIMEOUT_SECONDS = 30L

    fun get() = module(override = true) {
        single {
            GsonBuilder()
                .setLenient()
                .setFieldNamingStrategy(FieldNamingPolicy.IDENTITY)
                .create()
        }
        single<Interceptor>(named(QUALIFIER_LOGGING_INTERCEPTOR)) {
            LoggingInterceptor.Builder()
                .setLevel(Level.BASIC)
                .log(Platform.INFO)
                .request("Request")
                .response("Response")
                .build()
        }
        single(named(QUALIFIER_OKHTTP)) {
            OkHttpClient.Builder()
                .addInterceptor(get<Interceptor>(named(QUALIFIER_LOGGING_INTERCEPTOR)))
                .addNetworkInterceptor(StethoInterceptor())
                .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .build()
        }
        single { ExceptionFactory() }
        single<CallAdapter.Factory> { RxErrorHandlingCallAdapterFactory(get()) }
        single<Converter.Factory>(named(QUALIFIER_GSON_CONVERTER_FACTORY)) { GsonConverterFactory.create(get()) }
        single(named(QUALIFIER_PLACES_RETROFIT)) {
            Retrofit.Builder()
                .addConverterFactory(get(named(QUALIFIER_GSON_CONVERTER_FACTORY)))
                .addCallAdapterFactory(get())
                .baseUrl(EnvironmentConfig.foursquareBaseUrl())
                .client(get(named(QUALIFIER_OKHTTP)))
                .build()
        }

        single(named(QUALIFIER_MAPS_RETROFIT)) {
            Retrofit.Builder()
                .addConverterFactory(get(named(QUALIFIER_GSON_CONVERTER_FACTORY)))
                .addCallAdapterFactory(get())
                .baseUrl(EnvironmentConfig.googleMapsBaseUrl())
                .client(get(named(QUALIFIER_OKHTTP)))
                .build()
        }

        single { Geocoder(get(), Locale.US) }

        single<PlacesRepository> { DataPlacesRepository(get()) }
        single<MapsRepository> { DataMapsRepository(get()) }

        single { get<Retrofit>(named(QUALIFIER_PLACES_RETROFIT)).create(PlacesClient::class.java) }
        single { get<Retrofit>(named(QUALIFIER_MAPS_RETROFIT)).create(MapsClient::class.java) }
    }
}