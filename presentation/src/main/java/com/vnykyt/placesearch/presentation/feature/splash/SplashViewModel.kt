package com.vnykyt.placesearch.presentation.feature.splash

import com.vnykyt.placesearch.presentation.base.BaseViewModel
import timber.log.Timber

class SplashViewModel(
) : BaseViewModel() {

    fun onAnimationFinish() {
        Timber.e("onAnimationFinish")
//        router.navigateTo(AppDirections.ToMain)
    }
}