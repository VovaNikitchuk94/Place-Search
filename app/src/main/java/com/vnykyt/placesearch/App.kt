package com.vnykyt.placesearch

import android.app.Application
import android.os.Looper
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initRx()
        AndroidThreeTen.init(this)
        Stetho.initializeWithDefaults(this)
        Timber.plant(Timber.DebugTree())
    }

    private fun initRx() {
        RxJavaPlugins.setErrorHandler(DefaultRxErrorHandler())
        val asyncMainThreadScheduler = AndroidSchedulers.from(Looper.getMainLooper(), true)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { asyncMainThreadScheduler }
    }
}