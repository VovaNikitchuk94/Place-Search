package com.vnykyt.placesearch.presentation.system

import androidx.appcompat.app.AppCompatActivity
import java.lang.ref.WeakReference

class ActivityProvider {

    private var activityRef: WeakReference<AppCompatActivity>? = null

    val activity: AppCompatActivity? get() = activityRef?.get()

    fun setActivity(activity: AppCompatActivity) {
        activityRef = WeakReference(activity)
    }
}