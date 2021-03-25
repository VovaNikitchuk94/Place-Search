package com.vnykyt.placesearch.domain.di

import com.vnykyt.placesearch.domain.usecase.GetPlaceDetailsUseCase
import com.vnykyt.placesearch.domain.usecase.GetPlacesUseCase
import org.koin.dsl.module

object DomainModule {

    fun get() = module {
        factory { GetPlacesUseCase(get()) }
        factory { GetPlaceDetailsUseCase(get()) }
    }
}