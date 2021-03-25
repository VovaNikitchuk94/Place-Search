package com.vnykyt.placesearch.api.system

interface ResourcesManager {

    fun openLink(link: String)

    fun makeCall(phoneNumber: String)

    fun sendMessage(phoneNumber: String, message: String)
}