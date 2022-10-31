package com.will.moviedbapp.core.state

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    object Empty : UiState<Nothing>()
    object Error : UiState<Nothing>()
    object Success : UiState<Nothing>()
}
