package com.vnykyt.placesearch.presentation.feature.placedetails

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.jakewharton.rxbinding4.view.clicks
import com.vnykyt.placesearch.api.error.ConnectivityException
import com.vnykyt.placesearch.api.error.ErrorHandler
import com.vnykyt.placesearch.api.error.ReadableException
import com.vnykyt.placesearch.api.model.place.GroupType
import com.vnykyt.placesearch.presentation.R
import com.vnykyt.placesearch.presentation.base.errorDialog
import com.vnykyt.placesearch.presentation.databinding.FragmentPlaceDetailsBinding
import com.vnykyt.placesearch.presentation.extensions.throttleFirst
import com.vnykyt.placesearch.presentation.ui.DialogFactory
import com.vnykyt.placesearch.presentation.ui.loadImage
import com.vnykyt.placesearch.presentation.ui.setViewVisibility
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class PlaceDetailsFragment : Fragment(R.layout.fragment_place_details) {

    private val viewModel by viewModel<PlaceDetailsViewModel>()
    private val viewBinding: FragmentPlaceDetailsBinding by viewBinding()
    private val args by navArgs<PlaceDetailsFragmentArgs>()
    private var errorDialog: Dialog by errorDialog()
    private val errorHandler: ErrorHandler by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.actionCall.clicks()
            .throttleFirst()
            .subscribe { viewModel.onCallClicked() }

        viewBinding.actionWebsite.clicks()
            .throttleFirst()
            .subscribe { viewModel.onVisitWebsiteClicked() }

        viewBinding.actionDirectionsWalk.clicks()
            .throttleFirst()
            .subscribe { viewModel.onNavigateClicked() }

        viewBinding.textDistanceFromCenter.text = args.distance
        viewBinding.actionBack.setOnClickListener { findNavController().navigateUp() }
        viewModel.getPlacesDetails(args.placeId)
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

        viewModel.mapImageLink.observe(viewLifecycleOwner, { url ->
            viewBinding.imageMap.loadImage(url)
        })

        viewModel.venue.observe(viewLifecycleOwner, { place ->
            with(place) {
                viewBinding.textPlaceName.text = name
                categories.firstOrNull()?.let {
                    viewBinding.textPlaceCategory.text = it.name
                }
                location.formattedAddress.firstOrNull()?.let {
                    viewBinding.textAddress.text = it
                }
                val timeStatus = if (hours.status.isNotBlank()) hours.status else defaultHours.status
                viewBinding.textHours.text = timeStatus
                viewBinding.textPhoneNumber.text = contact.formattedPhone
                rating.toString().takeIf { it.isNotBlank() }?.let {
                    viewBinding.textRating.text = it
                    viewBinding.textRating.setTextColor(Color.parseColor(ratingColor))
                }
                attributes.groups.find { it.type == GroupType.PRICE }?.let {
                    viewBinding.textPrice.text = getString(R.string.place_details_price, price.message, it.summary)
                }

                with(viewBinding.root) {
                    setViewVisibility(price.message.isNotBlank(), viewBinding.textPrice.id)
                    setViewVisibility(rating.toString().isNotBlank(), viewBinding.textRating.id)
                    setViewVisibility(contact.formattedPhone.isNotBlank(), viewBinding.textPhoneNumber.id)
                    setViewVisibility(timeStatus.isNotBlank(), viewBinding.textHours.id)
                }
            }
        })
    }
}