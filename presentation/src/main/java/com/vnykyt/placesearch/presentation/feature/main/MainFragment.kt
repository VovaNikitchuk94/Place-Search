package com.vnykyt.placesearch.presentation.feature.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.jakewharton.rxbinding4.widget.textChanges
import com.vnykyt.placesearch.presentation.R
import com.vnykyt.placesearch.presentation.databinding.FragmentMainBinding
import com.vnykyt.placesearch.presentation.feature.main.adapter.PlaceListAdapter
import com.vnykyt.placesearch.presentation.feature.main.adapter.PlaceListItem
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MainFragment : Fragment(R.layout.fragment_main) {

    companion object {
        private const val TIME_WINDOW = 500L
    }

    private val viewModel by viewModel<MainViewModel>()
    private val viewBinding: FragmentMainBinding by viewBinding()
    private val adapter = PlaceListAdapter()

    override fun onViewCreated(view: View, savedState: Bundle?) {
        super.onViewCreated(view, savedState)
        viewBinding.placesList.adapter = adapter
        viewBinding.searchInput
            .textChanges()
            .throttleLast(TIME_WINDOW, TimeUnit.MILLISECONDS)
            .subscribe {
                viewModel.search(it.toString())
            }

        observeUpdates()
    }

    private fun observeUpdates() {
        viewModel.error.observe(viewLifecycleOwner, { error ->
            Timber.e("MainFragment >> $error")
        })

        viewModel.showProgress.observe(viewLifecycleOwner, { isLoading ->
            Timber.e("MainFragment >> $isLoading")
        })

        viewModel.places.observe(viewLifecycleOwner, { places ->
            Timber.e("MainFragment >> $places")
            adapter.submitList(places.venue.map { PlaceListItem(venue = it) })
        })
    }
}