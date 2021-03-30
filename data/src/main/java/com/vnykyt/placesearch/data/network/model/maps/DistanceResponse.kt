package com.vnykyt.placesearch.data.network.model.maps

import com.google.gson.annotations.SerializedName

data class DistanceResponse(
    @SerializedName("destination_addresses") val destinationAddresses: List<String>,
    @SerializedName("origin_addresses") val originAddresses: List<String>,
    val rows: List<Row>
)

data class Row(
    val elements: List<Element>
)

data class Element(
    val distance: Distance,
    val duration: Duration,
    val status: String
)

data class Distance(
    val text: String,
    val value: Int
)

data class Duration(
    val text: String,
    val value: Int
)