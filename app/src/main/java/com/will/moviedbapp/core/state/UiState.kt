package com.will.moviedbapp.core.state

import java.io.IOException

sealed class UiState<out T> {
    data class Success<T>(val data: T) : UiState<T>()

    data class Failure(
        val exception: Throwable,
        val message: String?
    ) : UiState<Nothing>() {
        val isNetworkError: Boolean get() = exception is IOException
    }

    object Loading : UiState<Nothing>()
    object Complete : UiState<Nothing>()
}
