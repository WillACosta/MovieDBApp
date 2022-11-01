package com.will.moviedbapp.presentation.model

import com.will.moviedbapp.domain.model.Movie

sealed class HomeAction {
    object LoadTrendingMovies : HomeAction()
    data class SearchMovies(val query: String) : HomeAction()
    data class ViewMovieDetails(val item: Movie) : HomeAction()
}
