package com.will.moviedbapp.modules.movie.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.will.moviedbapp.core.state.Result
import com.will.moviedbapp.modules.movie.domain.entity.Movie
import com.will.moviedbapp.modules.movie.domain.usecase.GetMovieUseCase
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val useCase: GetMovieUseCase) : ViewModel() {

    private val _movie = MutableLiveData<Result<Movie>>()
    val movie: LiveData<Result<Movie>> = _movie

    fun getMovie(id: String) {
        viewModelScope.launch {
            useCase(id.toInt()).collect {
                _movie.postValue(it)
            }
        }
    }
}
