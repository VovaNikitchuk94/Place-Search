package com.vnykyt.placesearch.presentation.ui

import android.view.View
import androidx.annotation.IdRes
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintSet

internal fun MotionLayout.setViewVisibility(isVisible: Boolean, @IdRes viewId: Int) {
    val visibility = if (isVisible) View.VISIBLE else View.GONE
    val startSet: ConstraintSet = getConstraintSet(id)
    startSet.setVisibility(viewId, visibility)
}