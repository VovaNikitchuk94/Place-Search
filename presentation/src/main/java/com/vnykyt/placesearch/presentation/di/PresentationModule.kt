package com.vnykyt.placesearch.presentation.di

import org.koin.dsl.module

object PresentationModule {

    fun get() = module(override = true) {
//        viewModel { MainViewModel(get(), get()) }
    }
}