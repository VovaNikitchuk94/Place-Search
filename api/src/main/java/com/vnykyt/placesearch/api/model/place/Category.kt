package com.vnykyt.placesearch.api.model.place

data class Category(
    val icon: Icon,
    val id: String,
    val name: String,
    val pluralName: String,
    val primary: Boolean,
    val shortName: String
) {

    companion object {

        val EMPTY = Category(
            icon = Icon.EMPTY,
            id = "",
            name = "",
            pluralName = "",
            primary = false,
            shortName = ""
        )
    }
}
