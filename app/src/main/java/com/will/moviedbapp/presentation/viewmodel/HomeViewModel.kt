package com.will.moviedbapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.will.moviedbapp.core.state.StateResult
import com.will.moviedbapp.domain.model.Movie
import com.will.moviedbapp.domain.usecase.GetTrendingMoviesUseCase
import com.will.moviedbapp.domain.usecase.SearchUseCase
import com.will.moviedbapp.presentation.model.HomeAction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getTrendingMoviesUseCase: GetTrendingMoviesUseCase,
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    private val _trendingMovies = MutableStateFlow<StateResult<List<Movie>>>(StateResult.Empty)
    val trendingMovies: StateFlow<StateResult<List<Movie>>> = _trendingMovies

    private val _searchQuery = MutableStateFlow("")

    val movies: LiveData<StateResult<List<Movie>>> = _searchQuery
        .debounce(300)
        .filter { text ->
            text.isNotEmpty()
        }
        .distinctUntilChanged()
        .flatMapLatest { text ->
            searchUseCase(text)
        }
        .flowOn(Dispatchers.Default)
        .asLiveData()

    fun handle(action: HomeAction) {
        when (action) {
            HomeAction.LoadTrendingMovies -> getTrendingMovies()
            is HomeAction.SearchMovies -> searchMovies(action.query)
            is HomeAction.ViewMovieDetails -> onViewMovieDetails(action.item)
        }
    }

    private fun onViewMovieDetails(item: Movie) {
        TODO("Not yet implemented")
    }

    private fun getTrendingMovies() {
        viewModelScope.launch {
            getTrendingMoviesUseCase(Unit)
                .collect {
                    _trendingMovies.value = it
                }
        }
    }

    private fun searchMovies(query: String) {
        _searchQuery.value = query
    }

}
