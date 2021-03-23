package com.vnykyt.placesearch.presentation.feature.splash

import androidx.lifecycle.ViewModel
import timber.log.Timber

class SplashViewModel(
) : ViewModel() {

    fun onAnimationFinish() {
        Timber.e("onAnimationFinish")
//        router.navigateTo(AppDirections.ToMain)
    }
}