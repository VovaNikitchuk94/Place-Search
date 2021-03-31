package com.vnykyt.placesearch.presentation.system

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.core.net.toUri
import com.livinglifetechway.quickpermissions_kotlin.runWithPermissions
import com.vnykyt.placesearch.api.system.ResourcesManager
import com.vnykyt.placesearch.presentation.R
import timber.log.Timber

internal class AppResourcesManager(private val activityProvider: ActivityProvider) : ResourcesManager {

    private val context get() = requireNotNull(activityProvider.activity)

    override fun openLink(link: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = link.toUri()
            flags = Intent.FLAG_ACTIVITY_NO_HISTORY
        }
        try {
            context.startActivity(Intent.createChooser(intent, context.getString(R.string.open_link_chooser_title)))
        } catch (e: ActivityNotFoundException) {
            Timber.w(e, "openLink")
        }
    }

    override fun makeCall(phoneNumber: String) {
        if (phoneNumber.isBlank()) return
        activityProvider.activity?.runOnUiThread {
            context.runWithPermissions(Manifest.permission.CALL_PHONE) {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phoneNumber"))
                context.startActivity(intent)
            }
        }
    }

    override fun sendMessage(phoneNumber: String, message: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:$phoneNumber")).apply {
            putExtra("sms_body", message)
        }
        try {
            context.startActivity(Intent.createChooser(intent, context.getString(R.string.send_sms_chooser_title)))
        } catch (e: ActivityNotFoundException) {
            Timber.w(e, "sendMessage")
        }
    }

    override fun startNavigation(origin: String, destination: String) {
        val googleMapsUrl = "http://maps.google.com/maps?saddr=47.6062,-122.3321&daddr=$destination"
        val uri = Uri.parse(googleMapsUrl)

        val googleMapsPackage = "com.google.android.apps.maps"
        val intent = Intent(Intent.ACTION_VIEW, uri).apply {
            setPackage(googleMapsPackage)
        }

        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Timber.w(e, "openLink")
        }
    }
}