package com.soumik.weathermvvm.utils

interface RequestCompleteListener<T> {
    fun onRequestCompleted(data:T)
    fun onRequestFailed(errorMessage: String)
}
