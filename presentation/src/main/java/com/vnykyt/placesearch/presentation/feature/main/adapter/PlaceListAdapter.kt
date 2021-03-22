package com.vnykyt.placesearch.presentation.feature.main.adapter

import android.view.View
import com.vnykyt.placesearch.presentation.R
import com.vnykyt.placesearch.presentation.base.BindableViewHolder
import com.vnykyt.placesearch.presentation.base.IdentifiableListAdapter
import com.vnykyt.placesearch.presentation.databinding.ItemPlaceBinding

class PlaceListAdapter : IdentifiableListAdapter<PlaceListItem>(
    layoutRes = R.layout.item_place,
    createViewHolder = { ViewHolder(it) }
) {

    class ViewHolder(itemView: View) : BindableViewHolder<PlaceListItem>(itemView) {
        private val binding = ItemPlaceBinding.bind(itemView)

        override fun bind(item: PlaceListItem) {
            binding.name.text = item.venue.name
        }
    }
}