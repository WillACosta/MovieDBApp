package com.will.moviedbapp.modules.home.domain.model

sealed class HomeAction {
    object LoadTrendingMovies : HomeAction()
    data class SearchMovies(val query: String) : HomeAction()
    data class ViewMovieDetails(val item: Movie) : HomeAction()
}
