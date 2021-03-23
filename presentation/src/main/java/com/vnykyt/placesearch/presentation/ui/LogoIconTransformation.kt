package com.vnykyt.placesearch.presentation.ui

import android.graphics.Bitmap
import com.squareup.picasso.Transformation
import com.vnykyt.placesearch.presentation.extensions.px
import jp.wasabeef.picasso.transformations.CropSquareTransformation
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation

class LogoIconTransformation : Transformation {

    companion object {

        private val DEFAULT_RADIUS = 8.px
    }

    private var width: Int = 0
    private var height: Int = 0
    private var radius: Int = 0

    override fun key(): String = "LogoIconTransformation(width = $width, height = $height, radius = $radius)"

    override fun transform(source: Bitmap): Bitmap {
        width = source.width
        height = source.height
        radius = if (source.width > DEFAULT_RADIUS * 4) DEFAULT_RADIUS else DEFAULT_RADIUS / 2
        return CropSquareTransformation().transform(RoundedCornersTransformation(radius, 0).transform(source)).also {
            source.recycle()
        }
    }
}