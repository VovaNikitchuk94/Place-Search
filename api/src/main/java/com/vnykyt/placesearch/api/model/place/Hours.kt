package com.vnykyt.placesearch.api.model.place

data class Hours(
    val isLocalHoliday: Boolean,
    val isOpen: Boolean,
    val status: String,
    val timeFrames: List<TimeFrame>
) {

    companion object {

        val EMPTY = Hours(
            isLocalHoliday = false,
            isOpen = false,
            status = "",
            timeFrames = emptyList()
        )
    }
}

data class TimeFrame(
    val days: String,
    val includesToday: Boolean,
    val open: List<Open>
) {

    companion object {

        val EMPTY = TimeFrame(
            days = "",
            includesToday = false,
            open = emptyList()
        )
    }
}


data class Open(
    val renderedTime: String
) {

    companion object {

        val EMPTY = Open(
            renderedTime = ""
        )
    }
}