package com.example.domain.responses

sealed class ResultRequired<out T> {
    data class Success<out T>(val result: T): ResultRequired<T>()
    data class Error(val throwable: Throwable): ResultRequired<Nothing>()
    object Loading: ResultRequired<Nothing>()
}