package com.testproject.myapplication.model

sealed class NetworkResponse<T> {
    data class Success<T>(val data: T) : NetworkResponse<T>()
    data class Error<T>(val error: Throwable? = null) : NetworkResponse<T>()
    class Loading<T> : NetworkResponse<T>()
}