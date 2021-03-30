package com.vnykyt.placesearch.domain.usecase

import com.vnykyt.placesearch.api.model.place.Venue
import com.vnykyt.placesearch.api.repository.MapsRepository
import com.vnykyt.placesearch.api.repository.PlacesRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class GetPlaceDetailsUseCase(
    private val placesRepository: PlacesRepository,
    private val mapsRepository: MapsRepository
) {

    fun execute(action: Action): Observable<Result> = placesRepository.placeDetails(action.id)
        .flatMap { venue ->
            mapsRepository.getImageMap(venue.location.coordinates)
                .map { Result.Success(venue, it) }
        }
        .toObservable()
        .cast(Result::class.java)
        .onErrorReturn(Result::Failure)
        .startWithItem(Result.Idle)
        .subscribeOn(Schedulers.io())

    data class Action(val id: String) : UseCase.Action

    sealed class Result : UseCase.Result {
        data class Success(val venue: Venue, val mapImageUrl: String) : Result()
        object Idle : Result()
        data class Failure(val throwable: Throwable) : Result()
    }
}