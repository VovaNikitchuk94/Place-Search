package com.vnykyt.placesearch.domain.validator

object ColorValidator {

    private val hexColorRegex: Regex by lazy { Regex("^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})\$") }

    fun isColorValid(color: String) = hexColorRegex.matches(color)
}