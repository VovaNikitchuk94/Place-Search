package com.vnykyt.placesearch.api.model.place

data class Price(
    val tier: Int,
    val message: String,
    val currency: String
) {

    companion object {

        val EMPTY = Price(
            tier = 0,
            message = "",
            currency = ""
        )
    }
}