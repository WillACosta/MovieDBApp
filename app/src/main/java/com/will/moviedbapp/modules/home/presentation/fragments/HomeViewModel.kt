@file:OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)

package com.will.moviedbapp.modules.home.presentation.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.will.moviedbapp.core.state.StateResult
import com.will.moviedbapp.modules.home.domain.usecase.SearchUseCase
import com.will.moviedbapp.modules.movie.domain.entity.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

class HomeViewModel(private val searchUseCase: SearchUseCase) : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: LiveData<String> = _searchQuery.asLiveData()

    val moviesResultList: LiveData<StateResult<List<Movie>>> = _searchQuery
        .debounce(800)
        .filter { text ->
            text.isNotEmpty()
        }
        .distinctUntilChanged()
        .flatMapLatest { text ->
            searchUseCase(text)
        }
        .flowOn(Dispatchers.Default)
        .asLiveData()

    fun searchMovies(query: String) {
        _searchQuery.value = query
    }
}
