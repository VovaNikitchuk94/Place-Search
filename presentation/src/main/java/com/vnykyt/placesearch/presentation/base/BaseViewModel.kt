package com.vnykyt.placesearch.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    protected val disposables: CompositeDisposable by lazy { CompositeDisposable() }

    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean> = _showProgress

    private val _error = MutableLiveData<Throwable?>()
    val error: LiveData<Throwable?> = _error

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

    protected fun showProgress() {
        _showProgress.value = true
    }

    protected fun hideProgress() {
        _showProgress.value = false
    }

    protected fun clearError() {
        _error.value = null
    }

    protected fun setError(error: Throwable) {
        _error.value = error
    }
}