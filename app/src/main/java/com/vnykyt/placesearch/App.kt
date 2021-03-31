package com.vnykyt.placesearch

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import com.vnykyt.placesearch.data.di.DataModule
import com.vnykyt.placesearch.domain.di.DomainModule
import com.vnykyt.placesearch.presentation.di.PresentationModule
import com.vnykyt.placesearch.presentation.system.ActivityProvider
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import org.koin.android.ext.android.get
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

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {

            override fun onActivityPaused(activity: Activity) = Unit

            override fun onActivityStarted(activity: Activity) = Unit

            override fun onActivityDestroyed(activity: Activity) = Unit

            override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) = Unit

            override fun onActivityStopped(activity: Activity) = Unit

            override fun onActivityCreated(activity: Activity, bundle: Bundle?) = Unit

            override fun onActivityResumed(activity: Activity) {
                (activity as? AppCompatActivity)?.let { get<ActivityProvider>().setActivity(it) }
            }
        })
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