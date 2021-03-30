package com.vnykyt.placesearch.api.model.place

data class Venue(
    val id: String,
    val name: String,
    val categories: List<Category>,
    val delivery: Delivery,
    val location: Location,
    val bestPhoto: Photo,
    val contact: Contact,
    val defaultHours: Hours,
    val hours: Hours,
    val rating: Double,
    val ratingColor: String,
    val url: String,
    val distance: String,
    val attributes: Attributes,
    val price: Price
) {

    companion object {

        val EMPTY = Venue(
            id = "",
            name = "",
            categories = emptyList(),
            delivery = Delivery.EMPTY,
            location = Location.EMPTY,
            bestPhoto = Photo.EMPTY,
            contact = Contact.EMPTY,
            defaultHours = Hours.EMPTY,
            hours = Hours.EMPTY,
            rating = 0.0,
            ratingColor = "",
            url = "",
            distance = "",
            attributes = Attributes(emptyList()),
            price = Price.EMPTY
        )
    }
}
