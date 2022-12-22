package com.will.moviedbapp.modules.movie.presentation.genres

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.will.moviedbapp.core.state.Result
import com.will.moviedbapp.modules.movie.domain.entity.MovieGenre
import com.will.moviedbapp.modules.movie.domain.usecase.GetGenresListUseCase
import kotlinx.coroutines.launch

class GenreViewModel(private val getGenresListUseCase: GetGenresListUseCase) : ViewModel() {
    private val _genres = MutableLiveData<Result<List<MovieGenre>>>()
    val genres: LiveData<Result<List<MovieGenre>>> = _genres

    init {
        getGenres()
    }

    private fun getGenres() {
        viewModelScope.launch {
            getGenresListUseCase(Unit)
                .collect {
                    _genres.postValue(it)
                }
        }
    }

}
