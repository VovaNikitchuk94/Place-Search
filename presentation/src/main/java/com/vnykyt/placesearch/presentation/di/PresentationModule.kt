package com.vnykyt.placesearch.presentation.di

import com.vnykyt.placesearch.api.error.ErrorHandler
import com.vnykyt.placesearch.api.system.ResourcesManager
import com.vnykyt.placesearch.presentation.base.AppErrorHandler
import com.vnykyt.placesearch.presentation.feature.main.MainViewModel
import com.vnykyt.placesearch.presentation.feature.map.MapViewModel
import com.vnykyt.placesearch.presentation.feature.placedetails.PlaceDetailsViewModel
import com.vnykyt.placesearch.presentation.feature.splash.SplashViewModel
import com.vnykyt.placesearch.presentation.system.ActivityProvider
import com.vnykyt.placesearch.presentation.system.AppResourcesManager
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PresentationModule {

    fun get() = module {
        single { ActivityProvider() }
        single<ResourcesManager> { AppResourcesManager(get()) }
        single<ErrorHandler> { AppErrorHandler() }
        viewModel { SplashViewModel() }
        viewModel { MainViewModel(get()) }
        viewModel { MapViewModel(get()) }
        viewModel { PlaceDetailsViewModel(get(), get()) }
    }
}