package com.vnykyt.placesearch.presentation.feature.placedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vnykyt.placesearch.api.model.place.Venue
import com.vnykyt.placesearch.api.system.ResourcesManager
import com.vnykyt.placesearch.domain.usecase.GetPlaceDetailsUseCase
import com.vnykyt.placesearch.domain.validator.ColorValidator
import com.vnykyt.placesearch.presentation.base.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo

class PlaceDetailsViewModel(
    private val getPlaceDetailsUseCase: GetPlaceDetailsUseCase,
    private val resourcesManager: ResourcesManager
) : BaseViewModel() {

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
        when (result) {
            is GetPlaceDetailsUseCase.Result.Idle -> {
                showProgress()
                clearError()
            }
            is GetPlaceDetailsUseCase.Result.Success -> {
                hideProgress()
                clearError()
                val ratingColor = getRatingColor(result.venue.ratingColor)
                _venue.value = result.venue.copy(ratingColor = ratingColor)
                _mapImageLink.value = result.mapImageUrl
            }
            is GetPlaceDetailsUseCase.Result.Failure -> {
                hideProgress()
                setError(result.throwable)
            }
        }
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
        return if (ColorValidator.isColorValid(placeColor)) placeColor else "#000000"
    }
}