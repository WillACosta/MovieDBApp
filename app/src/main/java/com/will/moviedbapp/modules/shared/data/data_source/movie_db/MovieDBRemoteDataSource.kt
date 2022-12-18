package com.will.moviedbapp.modules.shared.data.data_source.movie_db

import com.will.moviedbapp.modules.home.domain.model.Movie
import com.will.moviedbapp.modules.home.domain.model.MovieGenre

interface MovieDBRemoteDataSource {
    suspend fun getTrendingMovies(): List<Movie>
    suspend fun getGenres(): List<MovieGenre>
    suspend fun getMovie(id: Int): Movie
    suspend fun search(query: String): List<Movie>
}
