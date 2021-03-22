package com.vnykyt.placesearch.presentation.feature.main.adapter

import com.vnykyt.placesearch.api.model.place.Venue
import com.vnykyt.placesearch.presentation.base.Identifiable

data class PlaceListItem(
    val venue: Venue
) : Identifiable {

    override val id: String = venue.id
}
