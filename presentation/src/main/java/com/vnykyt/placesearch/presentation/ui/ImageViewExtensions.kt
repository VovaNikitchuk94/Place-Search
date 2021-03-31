package com.vnykyt.placesearch.presentation.ui

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation

fun ImageView.loadImage(
    uri: String?,
    @DrawableRes placeholder: Int = 0,
    transformation: Transformation,
    size: Pair<Int, Int>? = null
) {
    loadImage(uri, placeholder, listOf(transformation), size)
}

fun ImageView.loadImage(
    uri: String?,
    @DrawableRes placeholder: Int = 0,
    transformations: List<Transformation> = listOf(),
    size: Pair<Int, Int>? = null
) {
    Picasso.get()
        .load(uri?.takeIf { it.isNotBlank() })
        .centerCrop()
        .apply {
            if (drawable != null) placeholder(drawable) else if (placeholder != 0) placeholder(placeholder)
            if (size == null) fit() else resize(size.first, size.second)
        }
        .transform(transformations.toList())
        .into(this)
}

fun ImageView.setTint(@ColorRes colorRes: Int) {
    ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(ContextCompat.getColor(context, colorRes)))
}

fun Drawable?.bitmapDescriptorFromVector(): BitmapDescriptor? = this?.run {
    setBounds(0, 0, intrinsicWidth, intrinsicHeight)
    val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
    draw(Canvas(bitmap))
    BitmapDescriptorFactory.fromBitmap(bitmap)
}