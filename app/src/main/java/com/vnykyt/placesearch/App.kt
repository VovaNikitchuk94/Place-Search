package com.vnykyt.placesearch

import android.app.Application
import android.os.Looper
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import com.vnykyt.placesearch.data.di.DataModule
import com.vnykyt.placesearch.domain.di.DomainModule
import com.vnykyt.placesearch.presentation.di.PresentationModule
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDependencies()
        initRx()
        AndroidThreeTen.init(this)
        Stetho.initializeWithDefaults(this)
        Timber.plant(Timber.DebugTree())
    }

    private fun initDependencies() {
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    DataModule.get(),
                    DomainModule.get(),
                    PresentationModule.get()
                )
            )
        }
    }

    private fun initRx() {
        RxJavaPlugins.setErrorHandler(DefaultRxErrorHandler())
        val asyncMainThreadScheduler = AndroidSchedulers.from(Looper.getMainLooper(), true)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { asyncMainThreadScheduler }
    }
}