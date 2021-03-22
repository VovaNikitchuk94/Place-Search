package com.vnykyt.placesearch.api.model.place

data class Delivery(
    val id: String,
    val provider: Provider,
    val url: String
) {

    companion object {

        val EMPTY = Delivery(
            id = "",
            provider = Provider.EMPTY,
            url = ""
        )
    }
}

data class Provider(
    val icon: Icon,
    val name: String
) {
    companion object {

        val EMPTY = Provider(
            icon = Icon.EMPTY,
            name = ""
        )
    }
}
