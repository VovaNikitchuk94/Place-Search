package com.vnykyt.placesearch.presentation.ui

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.vnykyt.placesearch.presentation.R

object DialogFactory {

    fun createNoConnectivityDialog(context: Context): Dialog = MaterialAlertDialogBuilder(context)
        .setTitle(context.getString(R.string.error_title))
        .setMessage(context.getString(R.string.error_message_connectivity))
        .setPositiveButton(context.getString(R.string.button_ok), null)
        .setNeutralButton(context.getString(R.string.button_settings)) { _, _ ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                context.startActivity(Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY))
            } else {
                context.startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
            }
        }.create()

    fun createAppErrorDialog(context: Context, message: String): Dialog =
        MaterialAlertDialogBuilder(context)
            .setTitle(context.getString(R.string.error_title))
            .setMessage(message)
            .setPositiveButton(context.getString(R.string.button_ok), null)
            .create()
}