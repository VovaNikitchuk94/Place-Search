package com.vnykyt.placesearch.api.model.place

data class Group(
    val type: GroupType,
    val name: String,
    val summary: String
) {

    companion object {

        val EMPTY = Group(
            type = GroupType.UNKNOWN,
            name = "",
            summary = ""
        )
    }
}

enum class GroupType(val value: String) {

    UNKNOWN("unknown"),
    PRICE("price"),
    RESERVATIONS("reservations"),
    WIFI("wifi"),
    SERVER("serves"),
    PAYMENTS("payments");

    companion object {

        fun fromValue(value: String) = values().firstOrNull { it.value.equals(value, ignoreCase = true) } ?: UNKNOWN
    }
}