package com.vnykyt.placesearch.presentation.feature.main

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.jakewharton.rxbinding4.widget.queryTextChanges
import com.vnykyt.placesearch.presentation.R
import com.vnykyt.placesearch.presentation.ui.SpacingItemDecoration
import com.vnykyt.placesearch.presentation.databinding.FragmentMainBinding
import com.vnykyt.placesearch.presentation.extensions.px
import com.vnykyt.placesearch.presentation.feature.main.adapter.PlaceListAdapter
import com.vnykyt.placesearch.presentation.feature.main.adapter.PlaceListItem
import org.koin.android.viewmodel.ext.android.viewModel
import com.vnykyt.placesearch.presentation.extensions.throttleFirst

import timber.log.Timber

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel by viewModel<MainViewModel>()
    private val viewBinding: FragmentMainBinding by viewBinding()
    private val placeListAdapter = PlaceListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.placesList.apply {
            adapter = placeListAdapter
            addItemDecoration(SpacingItemDecoration(8.px, onSides = true, betweenItems = true, onTop = true))
            setHasFixedSize(true)
        }
        viewBinding.searchInput.queryTextChanges()
            .throttleFirst()
            .subscribe {
                viewModel.search(it.toString())
            }

        placeListAdapter.itemClickObservable
            .throttleFirst()
            .subscribe {
                viewModel.placeClicked(it.venue)
            }

        observeUpdates()
    }

    private fun observeUpdates() {
        viewModel.error.observe(viewLifecycleOwner, { error ->
            Timber.e("MainFragment >> $error")
        })

        viewModel.showProgress.observe(viewLifecycleOwner, { isLoading ->
            Timber.e("MainFragment >> $isLoading")
            viewBinding.progressBar.isVisible = isLoading
        })

        viewModel.places.observe(viewLifecycleOwner, { places ->
            Timber.e("MainFragment >> $places")
            placeListAdapter.submitList(places.venue.map { PlaceListItem(venue = it) })
        })
    }
}