package com.will.moviedbapp.modules.movie.presentation.featured

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.will.moviedbapp.core.state.Result
import com.will.moviedbapp.modules.movie.domain.entity.Movie
import com.will.moviedbapp.modules.movie.domain.usecase.GetTrendingMoviesUseCase
import kotlinx.coroutines.launch

class FeaturedMoviesViewModel(private val useCase: GetTrendingMoviesUseCase) : ViewModel() {

    private val _moviesList = MutableLiveData<Result<List<Movie>>>()
    val moviesList: LiveData<Result<List<Movie>>> = _moviesList

    fun getTrendingMovies() {
        viewModelScope.launch {
            useCase(Unit).collect {
                _moviesList.value = it
            }
        }
    }
}
