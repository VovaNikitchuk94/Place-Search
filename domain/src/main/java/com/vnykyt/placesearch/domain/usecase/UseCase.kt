package com.vnykyt.placesearch.domain.usecase

import io.reactivex.rxjava3.core.ObservableTransformer

interface UseCase<A : UseCase.Action, R : UseCase.Result> : ObservableTransformer<A, R> {

    interface Action

    interface Result
}