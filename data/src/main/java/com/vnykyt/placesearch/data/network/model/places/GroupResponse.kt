package com.vnykyt.placesearch.data.network.model.places

data class GroupResponse(
    val type: String,
    val name: String?,
    val summary: String?,
    val count: Int?
)