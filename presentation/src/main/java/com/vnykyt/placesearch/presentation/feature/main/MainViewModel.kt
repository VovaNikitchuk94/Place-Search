package com.vnykyt.placesearch.presentation.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vnykyt.placesearch.api.model.place.VenuesAndGeocode
import com.vnykyt.placesearch.api.model.place.Venue
import com.vnykyt.placesearch.domain.usecase.GetPlacesUseCase
import com.vnykyt.placesearch.presentation.base.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import timber.log.Timber

class MainViewModel(
    private val getPlacesUseCase: GetPlacesUseCase
) : BaseViewModel() {

    private val _places = MutableLiveData<List<Venue>>()
    internal val venuesAndGeocode: LiveData<List<Venue>> = _places

    fun search(queryText: String) {
        Timber.e("MainViewModel >> $queryText")

        getPlacesUseCase.execute(GetPlacesUseCase.Action(queryText))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result -> handleResult(result) }
            .addTo(disposables)
    }

    fun placeClicked(venue: Venue) {

    }

    private fun handleResult(result: GetPlacesUseCase.Result) {
        Timber.e("MainViewModel result >> $result")
        if (result is GetPlacesUseCase.Result.Success) {
            _places.value = result.venuesAndGeocode
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
}