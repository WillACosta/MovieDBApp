package com.will.moviedbapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.will.moviedbapp.core.state.Result
import com.will.moviedbapp.domain.entities.Movie
import com.will.moviedbapp.domain.usecases.GetTrendingMoviesUseCase
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
