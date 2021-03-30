package com.vnykyt.placesearch.presentation.feature.placedetails

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.isVisible
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
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class PlaceDetailsFragment : Fragment(R.layout.fragment_place_details) {

    private val viewModel by viewModel<PlaceDetailsViewModel>()
    private val viewBinding: FragmentPlaceDetailsBinding by viewBinding()
    private val args by navArgs<PlaceDetailsFragmentArgs>()
    private var errorDialog: Dialog by errorDialog()
    private val errorHandler: ErrorHandler by inject()
    private var lastProgress = 0f

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Timber.e("PlaceDetailsFragment args >> ${args.placeId}")
        viewBinding.root.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {}

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {}

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, progress: Float) {
//                if (p0 == null) return
//
//                if (progress - lastProgress > 0 && abs(progress - 1f) < 0.1f) {
//                    requireActivity().window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//                } else if (progress < 0.8f) {
//                    requireActivity().window?.decorView?.systemUiVisibility = 0
//                }
//                lastProgress = progress
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {}
        })

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
            Timber.e("PlaceDetailsFragment >> $url")
            viewBinding.imageMap.loadImage(url)
        })

        viewModel.venue.observe(viewLifecycleOwner, { place ->
            Timber.e("PlaceDetailsFragment >> $place")
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
                viewBinding.textHours.isVisible = timeStatus.isNotBlank()

                viewBinding.textPhoneNumber.text = contact.formattedPhone
                viewBinding.textPhoneNumber.isVisible = contact.formattedPhone.isNotBlank()

                rating.toString().takeIf { it.isNotBlank() }?.let {
                    viewBinding.textRating.text = it
                    viewBinding.textRating.setTextColor(Color.parseColor(ratingColor))
                }
                viewBinding.textRating.isVisible = rating.toString().isNotBlank()

                attributes.groups.find { it.type == GroupType.PRICE }?.let {
                    viewBinding.textPrice.text = "${price.message} (${it.summary})"
                }
                viewBinding.textPrice.isVisible = price.message.isNotBlank()

            }
        })
    }
}