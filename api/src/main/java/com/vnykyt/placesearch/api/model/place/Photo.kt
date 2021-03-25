package com.vnykyt.placesearch.api.model.place

data class Photo(
    val id: String,
    val height: Int,
    val width: Int,
    val prefix: String,
    val suffix: String
) {

    companion object {

        val EMPTY = Photo(
            id = "",
            height = 0,
            width = 0,
            prefix = "",
            suffix = ""
        )
    }
}
