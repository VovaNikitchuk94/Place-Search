package com.vnykyt.placesearch.domain.usecase

import com.vnykyt.placesearch.api.model.place.VenuesAndGeocode
import com.vnykyt.placesearch.api.repository.MapsRepository
import com.vnykyt.placesearch.api.repository.PlacesRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GetPlacesWithDistanceUseCase(
    private val placesRepository: PlacesRepository,
    private val mapsRepository: MapsRepository
) {

    fun execute(action: Action): Observable<Result> = placesRepository.searchPlace(action.query)
        .flatMap { venuesAndGeocode ->
            Observable.fromIterable(venuesAndGeocode.venue)
                .flatMapSingle { venue ->
                    mapsRepository.getMapDistance(venue.location.coordinates)
                        .onErrorResumeNext { Single.just("") }
                        .map { venue.copy(distance = it) }
                }
                .toList()
                .flatMap { Single.just(venuesAndGeocode.copy(venue = it)) }
        }
        .toObservable()
        .map { Result.Success(it) }
        .cast(Result::class.java)
        .onErrorReturn(Result::Failure)
        .startWithItem(Result.Idle)
        .subscribeOn(Schedulers.io())

    data class Action(val query: String) : UseCase.Action

    sealed class Result : UseCase.Result {
        data class Success(val venues: VenuesAndGeocode) : Result()
        object Idle : Result()
        data class Failure(val throwable: Throwable) : Result()
    }
}