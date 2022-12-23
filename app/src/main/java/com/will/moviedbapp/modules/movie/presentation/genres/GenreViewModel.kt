@file:OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)

package com.will.moviedbapp.modules.movie.presentation.genres

import androidx.lifecycle.*
import com.will.moviedbapp.core.state.Result
import com.will.moviedbapp.modules.movie.domain.entity.Movie
import com.will.moviedbapp.modules.movie.domain.entity.MovieGenre
import com.will.moviedbapp.modules.movie.domain.usecase.DiscoverMoviesUseCase
import com.will.moviedbapp.modules.movie.domain.usecase.GetGenresListUseCase
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

    private val _selectedGenres = MutableStateFlow<Array<Int>>(arrayOf())

    val moviesByGenres: LiveData<Result<List<Movie>>> = _selectedGenres
        .debounce(800)
        .filter { genresList ->
            genresList.isNotEmpty()
        }
        .distinctUntilChanged()
        .flatMapLatest {
            discoverMovies(it)
        }
        .flowOn(Dispatchers.Default)
        .asLiveData()

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
