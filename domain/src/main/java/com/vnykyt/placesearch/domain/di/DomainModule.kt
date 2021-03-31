package com.vnykyt.placesearch.domain.di

import com.vnykyt.placesearch.domain.usecase.GetPlaceDetailsUseCase
import com.vnykyt.placesearch.domain.usecase.GetPlacesWithDistanceUseCase
import org.koin.dsl.module

object DomainModule {

    fun get() = module {
        factory { GetPlacesWithDistanceUseCase(get(), get()) }
        factory { GetPlaceDetailsUseCase(get(), get()) }
    }
}