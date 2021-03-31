package com.vnykyt.placesearch.api.model.place

data class Contact(
    val formattedPhone: String,
    val phone: String,
    val twitter: String
) {

    companion object {

        val EMPTY = Contact(
            formattedPhone = "",
            phone = "",
            twitter = ""
        )
    }
}
