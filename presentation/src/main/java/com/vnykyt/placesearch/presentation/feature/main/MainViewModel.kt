package com.vnykyt.placesearch.presentation.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vnykyt.placesearch.api.model.place.Venue
import com.vnykyt.placesearch.domain.usecase.GetPlacesWithDistanceUseCase
import com.vnykyt.placesearch.presentation.base.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import timber.log.Timber

class MainViewModel(
    private val getPlacesWithDistanceUseCase: GetPlacesWithDistanceUseCase
) : BaseViewModel() {

    private val _places = MutableLiveData<List<Venue>>()
    internal val venuesAndGeocode: LiveData<List<Venue>> = _places

    fun search(queryText: String) {
        Timber.e("MainViewModel >> $queryText")

        getPlacesWithDistanceUseCase.execute(GetPlacesWithDistanceUseCase.Action(queryText))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result -> handleResult(result) }
            .addTo(disposables)
    }

    private fun handleResult(result: GetPlacesWithDistanceUseCase.Result) {
        Timber.e("MainViewModel result >> $result")
        if (result is GetPlacesWithDistanceUseCase.Result.Success) {
            _places.value = result.venues.venue
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