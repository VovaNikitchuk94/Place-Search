package com.vnykyt.placesearch.presentation.feature.main.adapter

import android.view.View
import com.vnykyt.placesearch.presentation.R
import com.vnykyt.placesearch.presentation.base.BindableViewHolder
import com.vnykyt.placesearch.presentation.base.IdentifiableListAdapter
import com.vnykyt.placesearch.presentation.databinding.ItemPlaceBinding
import com.vnykyt.placesearch.presentation.ui.LogoIconTransformation
import com.vnykyt.placesearch.presentation.ui.loadImage
import com.vnykyt.placesearch.presentation.ui.setTint
import timber.log.Timber

class PlaceListAdapter : IdentifiableListAdapter<PlaceListItem>(
    layoutRes = R.layout.item_place,
    createViewHolder = { ViewHolder(it) }
) {

    companion object {
        private const val ICON_SIZE = 88
    }

    class ViewHolder(itemView: View) : BindableViewHolder<PlaceListItem>(itemView) {
        private val binding = ItemPlaceBinding.bind(itemView)

        override fun bind(item: PlaceListItem) {
            with(item) {
                val iconCategoryUrl = venue.categories.firstOrNull()?.icon?.let { icon ->
                    "${icon.prefix}$ICON_SIZE${icon.suffix}"
                }
                binding.textName.text = venue.name
                binding.placeIcon.apply {
                    loadImage(iconCategoryUrl, R.drawable.ic_placeholder, LogoIconTransformation())
                    setTint(R.color.gray)
                }
                binding.textCategory.text = venue.categories.firstOrNull()?.name
                binding.textAddress.text = venue.location.formattedAddress.firstOrNull() ?: ""
//                binding.textPhoneNumber.text = venue.
            }
        }
    }
}