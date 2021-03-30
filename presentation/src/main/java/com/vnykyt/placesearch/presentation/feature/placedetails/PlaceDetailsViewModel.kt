package com.vnykyt.placesearch.presentation.feature.placedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vnykyt.placesearch.api.model.place.Venue
import com.vnykyt.placesearch.api.system.ResourcesManager
import com.vnykyt.placesearch.domain.usecase.GetPlaceDetailsUseCase
import com.vnykyt.placesearch.presentation.base.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import timber.log.Timber

class PlaceDetailsViewModel(
    private val getPlaceDetailsUseCase: GetPlaceDetailsUseCase,
    private val resourcesManager: ResourcesManager
) : BaseViewModel() {

    private val hexColorRegex: Regex by lazy { Regex("^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})\$") }

    private val _venue = MutableLiveData<Venue>()
    internal val venue: LiveData<Venue> = _venue

    private val _mapImageLink = MutableLiveData<String>()
    internal val mapImageLink: LiveData<String> = _mapImageLink

    fun getPlacesDetails(id: String) {
        getPlaceDetailsUseCase.execute(GetPlaceDetailsUseCase.Action(id))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result -> handleResult(result) }
            .addTo(disposables)
    }

    private fun handleResult(result: GetPlaceDetailsUseCase.Result) {
        Timber.e("PlaceDetailsViewModel result >> $result")
        if (result is GetPlaceDetailsUseCase.Result.Success) {
            val ratingColor = getRatingColor(result.venue.ratingColor)
            _venue.value = result.venue.copy(ratingColor = ratingColor)
            _mapImageLink.value = result.mapImageUrl
            hideProgress()
        }
//        when (result) {
//            is GetPlacesUseCase.Result.Success -> {
//                _places.setValue(places)
//                hideProgress()
//            }
//            is GetPlacesUseCase.Result.Idle -> {
//                showProgress()
//            }
//            is GetPlacesUseCase.Result.Failure -> {
//                hideProgress()
//            }
//        }
    }

    fun onCallClicked() {
        resourcesManager.makeCall(requireNotNull(_venue.value).contact.formattedPhone)
    }

    fun onVisitWebsiteClicked() {
        resourcesManager.openLink(requireNotNull(_venue.value).url)
    }

    fun onNavigateClicked() {
        resourcesManager.startNavigation(destination = requireNotNull(_venue.value).location.coordinates)
    }

    private fun getRatingColor(color: String): String {
        val placeColor = "#$color"
        return if (isColorValid(placeColor)) placeColor else "#000000"
    }

    private fun isColorValid(color: String) = hexColorRegex.matches(color)

}