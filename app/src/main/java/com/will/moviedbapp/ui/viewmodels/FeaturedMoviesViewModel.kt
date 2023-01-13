package com.will.moviedbapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.will.moviedbapp.core.state.UiState
import com.will.moviedbapp.domain.entities.Movie
import com.will.moviedbapp.domain.usecases.GetTrendingMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FeaturedMoviesViewModel(private val useCase: GetTrendingMoviesUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getTrendingMovies() {
        viewModelScope.launch {
            useCase()
                .collect { listResult ->
                    listResult.fold(
                        { _uiState.emit(UiState.Success(it)) },
                        { _uiState.emit(UiState.Failure(it, null)) }
                    )
                }
        }
    }
}
