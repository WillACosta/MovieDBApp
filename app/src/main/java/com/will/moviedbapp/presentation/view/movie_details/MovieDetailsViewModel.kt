package com.will.moviedbapp.presentation.view.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.will.moviedbapp.core.state.StateResult
import com.will.moviedbapp.domain.model.Movie
import com.will.moviedbapp.domain.usecase.GetMovieUseCase
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val useCase: GetMovieUseCase) : ViewModel() {

    private val _movie = MutableLiveData<StateResult<Movie>>()
    val movie: LiveData<StateResult<Movie>> = _movie

    fun getMovie(id: String) {
        viewModelScope.launch {
            useCase(id.toInt()).collect {
                _movie.postValue(it)
            }
        }
    }

}
