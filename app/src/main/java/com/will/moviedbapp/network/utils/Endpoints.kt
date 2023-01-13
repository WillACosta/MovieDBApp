package com.will.moviedbapp.network.utils

abstract class Endpoints {
    companion object {
        const val TRENDING = "trending/movie/week"
        const val MOVIE_DETAIL = "movie/{movie_id}"
        const val GENRES = "genre/movie/list"
        const val SEARCH = "search/movie"
        const val DISCOVER = "discover/movie"
    }
}
