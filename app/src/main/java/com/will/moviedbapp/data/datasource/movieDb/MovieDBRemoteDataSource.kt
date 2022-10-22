package com.will.moviedbapp.data.datasource.movieDb

import com.will.moviedbapp.data.model.Movie

interface MovieDBRemoteDataSource {
    suspend fun getTrendingMovies(): List<Movie>
    suspend fun getMovie(id: Int): Movie
    suspend fun search(query: String): List<Movie>
}
