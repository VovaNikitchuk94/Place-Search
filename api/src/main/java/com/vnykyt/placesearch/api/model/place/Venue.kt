package com.vnykyt.placesearch.api.model.place

data class Venue(
    val id: String,
    val name: String,
    val categories: List<Category>,
    val delivery: Delivery,
    val location: Location
) {

    companion object {

        val EMPTY = Venue(
            id = "",
            name = "",
            categories = emptyList(),
            delivery = Delivery.EMPTY,
            location = Location.EMPTY
        )
    }
}
