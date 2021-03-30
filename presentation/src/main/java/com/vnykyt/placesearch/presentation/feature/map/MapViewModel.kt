package com.vnykyt.placesearch.presentation.feature.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vnykyt.placesearch.api.model.place.VenuesAndGeocode
import com.vnykyt.placesearch.domain.usecase.GetPlacesWithDistanceUseCase
import com.vnykyt.placesearch.presentation.base.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo

class MapViewModel(
    private val getPlacesUseCase: GetPlacesWithDistanceUseCase
) : BaseViewModel() {

    private val _venuesAndGeocode = MutableLiveData<VenuesAndGeocode>()
    internal val venuesAndGeocode: LiveData<VenuesAndGeocode> = _venuesAndGeocode

    fun search(queryText: String) {
        getPlacesUseCase.execute(GetPlacesWithDistanceUseCase.Action(queryText))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result -> handleResult(result) }
            .addTo(disposables)
    }

    private fun handleResult(result: GetPlacesWithDistanceUseCase.Result) {
        when (result) {
            is GetPlacesWithDistanceUseCase.Result.Idle -> {
                showProgress()
                clearError()
            }
            is GetPlacesWithDistanceUseCase.Result.Success -> {
                hideProgress()
                clearError()
                _venuesAndGeocode.value = result.venues
            }
            is GetPlacesWithDistanceUseCase.Result.Failure -> {
                hideProgress()
                setError(result.throwable)
            }
        }
    }
}