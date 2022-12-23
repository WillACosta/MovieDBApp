package com.will.moviedbapp.modules.shared.data.datasource.remote.movie

import com.will.moviedbapp.modules.movie.domain.entity.Movie
import com.will.moviedbapp.modules.movie.domain.entity.MovieGenre

interface MovieDBRemoteDataSource {
    suspend fun getTrendingMovies(): List<Movie>
    suspend fun getGenres(): List<MovieGenre>
    suspend fun getMovie(id: Int): Movie
    suspend fun search(query: String): List<Movie>
    suspend fun discoverMovies(genresId: Array<Int>?): List<Movie>
}
