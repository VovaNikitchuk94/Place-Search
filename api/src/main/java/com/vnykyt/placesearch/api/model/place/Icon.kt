package com.vnykyt.placesearch.api.model.place

data class Icon(
    val prefix: String,
    val suffix: String
) {

    companion object {

        val EMPTY = Icon(
            prefix = "",
            suffix = ""
        )
    }
}