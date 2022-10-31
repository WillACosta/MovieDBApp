package com.will.moviedbapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.will.moviedbapp.core.state.StateResult
import com.will.moviedbapp.domain.model.Movie
import com.will.moviedbapp.domain.usecase.GetTrendingMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val getTrendingMoviesUseCase: GetTrendingMoviesUseCase) : ViewModel() {

    private val _trendingMovies = MutableStateFlow<StateResult<List<Movie>>>(StateResult.Empty)
    val trendingMovies: StateFlow<StateResult<List<Movie>>> = _trendingMovies

    init {
        getTrendingMovies()
    }

    private fun getTrendingMovies() {
        viewModelScope.launch {
            getTrendingMoviesUseCase(Unit)
                .collect {
                    _trendingMovies.value = it
                }
        }
    }

}
