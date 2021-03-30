package com.vnykyt.placesearch.presentation.base

import android.app.Dialog
import androidx.fragment.app.Fragment
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Dismisses error dialog before showing another
 */
class ErrorDialogProperty : ReadWriteProperty<Fragment, Dialog> {

    private var errorDialog: Dialog? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): Dialog = requireNotNull(errorDialog)

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: Dialog) {
        if (errorDialog?.isShowing == true) return
        errorDialog?.dismiss()
        errorDialog = value
        errorDialog?.show()
    }
}

@Suppress("unused")
fun Fragment.errorDialog() = ErrorDialogProperty()