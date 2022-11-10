package com.will.moviedbapp.core.state

sealed class UiState {
    object Loading : UiState()
    object Empty : UiState()
    object Error : UiState()
    object Success : UiState()
}
