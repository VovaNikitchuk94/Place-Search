package com.vnykyt.placesearch.presentation.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jakewharton.rxrelay3.PublishRelay
import com.vnykyt.placesearch.api.model.place.Venue
import com.vnykyt.placesearch.domain.usecase.GetPlacesWithDistanceUseCase
import com.vnykyt.placesearch.presentation.base.BaseViewModel
import com.vnykyt.placesearch.presentation.extensions.throttleFirst
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo

class MainViewModel(
    private val getPlacesWithDistanceUseCase: GetPlacesWithDistanceUseCase
) : BaseViewModel() {

    private val searchPublishSubject = PublishRelay.create<String>()

    private val _places = MutableLiveData<List<Venue>>()
    internal val venuesAndGeocode: LiveData<List<Venue>> = _places

    init {
        configureSearch()
    }

    fun onInputStateChanged(query: String) {
        searchPublishSubject.accept(query.trim())
    }

    private fun configureSearch() {
        searchPublishSubject
            .throttleFirst()
            .distinctUntilChanged()
            .switchMap {
                getPlacesWithDistanceUseCase.execute(GetPlacesWithDistanceUseCase.Action(it))
            }
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
                _places.value = result.venues.venue
            }
            is GetPlacesWithDistanceUseCase.Result.Failure -> {
                hideProgress()
                setError(result.throwable)
            }
        }
    }
}