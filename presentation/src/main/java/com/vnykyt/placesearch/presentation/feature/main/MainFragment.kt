package com.vnykyt.placesearch.presentation.feature.main

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.widget.queryTextChanges
import com.vnykyt.placesearch.api.error.ConnectivityException
import com.vnykyt.placesearch.api.error.ErrorHandler
import com.vnykyt.placesearch.api.error.ReadableException
import com.vnykyt.placesearch.presentation.R
import com.vnykyt.placesearch.presentation.base.errorDialog
import com.vnykyt.placesearch.presentation.databinding.FragmentMainBinding
import com.vnykyt.placesearch.presentation.extensions.px
import com.vnykyt.placesearch.presentation.extensions.throttleFirst
import com.vnykyt.placesearch.presentation.feature.main.adapter.PlaceListAdapter
import com.vnykyt.placesearch.presentation.feature.main.adapter.PlaceListItem
import com.vnykyt.placesearch.presentation.ui.DialogFactory
import com.vnykyt.placesearch.presentation.ui.SpacingItemDecoration
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel by viewModel<MainViewModel>()
    private val viewBinding: FragmentMainBinding by viewBinding()
    private var errorDialog: Dialog by errorDialog()
    private val errorHandler: ErrorHandler by inject()
    private val placeListAdapter = PlaceListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.placesList.apply {
            adapter = placeListAdapter
            addItemDecoration(SpacingItemDecoration(8.px, onSides = true, betweenItems = true, onTop = true))
            setHasFixedSize(true)
        }
        viewBinding.searchInput.queryTextChanges()
            .skipInitialValue()
            .subscribe {
                viewModel.onInputStateChanged(it.toString())
            }

        placeListAdapter.itemClickObservable
            .throttleFirst()
            .subscribe {
                viewBinding.root.findNavController().navigate(MainFragmentDirections.actionPlaceDetailsScreen(it.id, it.venue.distance))
            }

        viewBinding.actionOpenMap.clicks()
            .throttleFirst()
            .subscribe {
                viewBinding.root.findNavController().navigate(MainFragmentDirections.actionFullMapScreen(viewBinding.searchInput.query.toString()))
            }

        observeUpdates()
    }

    private fun observeUpdates() {
        viewModel.error.observe(viewLifecycleOwner, { error ->
            when (error) {
                is ConnectivityException -> errorDialog = DialogFactory.createNoConnectivityDialog(requireContext())
                is ReadableException -> errorDialog = DialogFactory.createAppErrorDialog(requireContext(), error.message)
                else -> errorHandler(error)
            }
        })

        viewModel.showProgress.observe(viewLifecycleOwner, { isLoading ->
            viewBinding.progressBar.isVisible = isLoading
        })

        viewModel.venuesAndGeocode.observe(viewLifecycleOwner, { places ->
            placeListAdapter.submitList(places.map { PlaceListItem(venue = it) })
            viewBinding.actionOpenMap.isVisible = places.isNotEmpty()
            viewBinding.emptyStateView.isVisible = places.isEmpty()
        })
    }
}