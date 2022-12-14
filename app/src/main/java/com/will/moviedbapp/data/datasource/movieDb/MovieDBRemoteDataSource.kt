package com.will.moviedbapp.data.datasource.movieDb

import com.will.moviedbapp.domain.model.Movie
import com.will.moviedbapp.domain.model.MovieGenre

interface MovieDBRemoteDataSource {
    suspend fun getTrendingMovies(): List<Movie>
    suspend fun getGenres(): List<MovieGenre>
    suspend fun getMovie(id: Int): Movie
    suspend fun search(query: String): List<Movie>
}
