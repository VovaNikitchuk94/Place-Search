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

    private val _venue = MutableLiveData<Venue>()
    internal val venue: LiveData<Venue> = _venue

    fun getPlacesDetails(id: String) {
        getPlaceDetailsUseCase.execute(GetPlaceDetailsUseCase.Action(id))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result -> handleResult(result) }
            .addTo(disposables)
    }

    private fun handleResult(result: GetPlaceDetailsUseCase.Result) {
        Timber.e("PlaceDetailsViewModel result >> $result")
        if (result is GetPlaceDetailsUseCase.Result.Success) {
            _venue.value = result.venue
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

}