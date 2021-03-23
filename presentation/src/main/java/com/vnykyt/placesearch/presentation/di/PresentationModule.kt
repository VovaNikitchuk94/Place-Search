package com.vnykyt.placesearch.presentation.di

import com.vnykyt.placesearch.presentation.feature.main.MainViewModel
import com.vnykyt.placesearch.presentation.feature.splash.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PresentationModule {

    fun get() = module {
        viewModel { SplashViewModel() }
        viewModel { MainViewModel(get()) }
    }
}