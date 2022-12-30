@file:OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)

package com.will.moviedbapp.ui.viewmodels

import androidx.lifecycle.*
import com.will.moviedbapp.core.state.Result
import com.will.moviedbapp.domain.entities.Movie
import com.will.moviedbapp.domain.entities.MovieGenre
import com.will.moviedbapp.domain.usecases.DiscoverMoviesUseCase
import com.will.moviedbapp.domain.usecases.GetGenresListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class GenreViewModel(
    private val getGenresListUseCase: GetGenresListUseCase,
    private val discoverMovies: DiscoverMoviesUseCase
) : ViewModel() {
    private val _genres = MutableLiveData<Result<List<MovieGenre>>>()
    val genres: LiveData<Result<List<MovieGenre>>> = _genres

    private val _selectedGenres = MutableStateFlow<Array<Int>?>(null)

    val moviesByGenres: LiveData<Result<List<Movie>>> = _selectedGenres
        .debounce(800)
        .distinctUntilChanged()
        .flatMapLatest {
            discoverMovies(it)
        }
        .flowOn(Dispatchers.Default)
        .asLiveData()

    val selectedIds = mutableListOf<Int>()

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

    fun updateSelectedGenres(genresIdList: Array<Int>) {
        _selectedGenres.value = genresIdList
    }

}
