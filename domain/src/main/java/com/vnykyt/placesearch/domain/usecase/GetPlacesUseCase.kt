package com.vnykyt.placesearch.domain.usecase

import com.vnykyt.placesearch.api.model.place.Venue
import com.vnykyt.placesearch.api.model.place.VenuesAndGeocode
import com.vnykyt.placesearch.api.repository.MapsRepository
import com.vnykyt.placesearch.api.repository.PlacesRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GetPlacesUseCase(
    private val placesRepository: PlacesRepository,
    private val mapsRepository: MapsRepository
) {

    fun execute(action: Action): Observable<Result> = placesRepository.searchPlace(action.query)
        .flattenAsObservable{
            it.venue
        }
        .flatMapSingle { venue ->
            mapsRepository.getMapDistance(venue.location.coordinates)
                .onErrorResumeNext { Single.just("") }
                .map { venue.copy(distance = it) }
        }
        .toList()
        .toObservable()
        .map { Result.Success(it) }
        .cast(Result::class.java)
        .onErrorReturn(Result::Failure)
        .startWithItem(Result.Idle)
        .subscribeOn(Schedulers.io())

    data class Action(val query: String) : UseCase.Action

    sealed class Result : UseCase.Result {
        data class Success(val venuesAndGeocode: List<Venue>) : Result()
        object Idle : Result()
        data class Failure(val throwable: Throwable) : Result()
    }
}