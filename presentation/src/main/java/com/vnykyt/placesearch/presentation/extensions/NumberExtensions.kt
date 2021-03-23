package com.vnykyt.placesearch.presentation.extensions

import android.content.res.Resources

@Suppress("UNCHECKED_CAST")
val <T : Number> T.dp: T
    get() = (this.toDouble() / Resources.getSystem().displayMetrics.density).toInt() as T

@Suppress("UNCHECKED_CAST")
val <T : Number> T.px: T
    get() = (this.toDouble() * Resources.getSystem().displayMetrics.density).toInt() as T

@Suppress("UNCHECKED_CAST")
val <T : Number> T.pxFromSp: T
    get() = (this.toDouble() * Resources.getSystem().displayMetrics.scaledDensity).toInt() as T