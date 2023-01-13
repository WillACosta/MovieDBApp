package com.will.moviedbapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.will.moviedbapp.data.utils.Resource
import com.will.moviedbapp.domain.entities.Movie
import com.will.moviedbapp.domain.usecases.GetMovieUseCase
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val useCase: GetMovieUseCase) : ViewModel() {

    private val _movie = MutableLiveData<Resource<Movie>>()
    val movie: LiveData<Resource<Movie>> = _movie

    fun getMovie(id: String) {
//        viewModelScope.launch {
//            useCase(id.toInt()).collect {
//                _movie.postValue(it)
//            }
//        }
    }
}
