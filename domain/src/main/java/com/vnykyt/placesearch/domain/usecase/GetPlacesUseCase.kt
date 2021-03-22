package com.vnykyt.placesearch.domain.usecase

import com.vnykyt.placesearch.api.model.place.Places
import com.vnykyt.placesearch.api.repository.PlacesRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class GetPlacesUseCase(
    private val placesRepository: PlacesRepository
) {

    fun execute(action: Action): Observable<Result> = placesRepository.searchPlace(action.query)
        .toObservable()
        .map { Result.Success(it) }
        .cast(Result::class.java)
        .onErrorReturn(Result::Failure)
        .startWithItem(Result.Idle)
        .subscribeOn(Schedulers.io())

    data class Action(val query: String) : UseCase.Action

    sealed class Result : UseCase.Result {
        data class Success(val places: Places) : Result()
        object Idle : Result()
        data class Failure(val throwable: Throwable) : Result()
    }
}