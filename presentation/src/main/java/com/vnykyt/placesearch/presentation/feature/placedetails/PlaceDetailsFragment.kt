package com.vnykyt.placesearch.presentation.feature.placedetails

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.jakewharton.rxbinding4.view.clicks
import com.vnykyt.placesearch.presentation.R
import com.vnykyt.placesearch.presentation.databinding.FragmentPlaceDetailsStartBinding
import com.vnykyt.placesearch.presentation.extensions.throttleFirst
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber
import kotlin.math.abs

class PlaceDetailsFragment : Fragment(R.layout.fragment_place_details_start) {

    private val viewModel by viewModel<PlaceDetailsViewModel>()
    private val viewBinding: FragmentPlaceDetailsStartBinding by viewBinding()
    private var lastProgress = 0f

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args by navArgs<PlaceDetailsFragmentArgs>()
        Timber.e("PlaceDetailsFragment args >> ${args.placeId}")
        viewBinding.root.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
            }

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, progress: Float) {
                if (p0 == null) return

                if (progress - lastProgress > 0 && abs(progress - 1f) < 0.1f) {
                    requireActivity().window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                } else if (progress < 0.8f) {
                    requireActivity().window?.decorView?.systemUiVisibility = 0
                }
                lastProgress = progress
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
            }
        })

        viewBinding.buttonCall.clicks()
            .throttleFirst()
            .subscribe {
                viewModel.onCallClicked()
            }

        viewBinding.buttonWebsite.clicks()
            .throttleFirst()
            .subscribe {
                viewModel.onVisitWebsiteClicked()
            }

        viewModel.getPlacesDetails(args.placeId)
//        viewBinding.imageMap.loadImage("https://maps.googleapis.com/maps/api/staticmap?" +
//            "center=47.6062,-122.3321&" +
//            "zoom=13" +
//            "&size=400x400" +
//            "&maptype=roadmap" +
//            "&markers=color:blue%7Clabel:A%7C47.605925,-122.3340619" +
//            "&markers=color:red%7Clabel:B%7C47.6062,-122.3321\n" +
//            "&key=AIzaSyBsxCxoDEPykPuPn84H335z3aGNKat-54k")
        observeUpdates()
    }

    private fun observeUpdates() {
        viewModel.error.observe(viewLifecycleOwner, { error ->
            Timber.e("PlaceDetailsFragment >> $error")
        })
        viewModel.showProgress.observe(viewLifecycleOwner, { isLoading ->
            Timber.e("PlaceDetailsFragment >> $isLoading")
//            viewBinding.progressBar.isVisible = isLoading
        })

        viewModel.venue.observe(viewLifecycleOwner, { place ->
            Timber.e("PlaceDetailsFragment >> $place")
            with(place) {
                viewBinding.textPlaceName.text = name
                categories.firstOrNull()?.let {
                    viewBinding.textPlaceCategory.text = it.name
                }
                viewBinding.textDistanceFromCenter.text = "900m"
                location.formattedAddress.firstOrNull()?.let {
                    viewBinding.textAddress.text = it
                }
                viewBinding.textHours.text = "${hours.isOpen} ${hours.status}"
                viewBinding.textPhoneNumber.text = contact.formattedPhone
                viewBinding.textRating.text = rating.toString()
            }
        })
    }
}