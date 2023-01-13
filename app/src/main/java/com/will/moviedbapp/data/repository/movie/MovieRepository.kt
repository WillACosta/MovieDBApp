package com.will.moviedbapp.data.repository.movie

import com.will.moviedbapp.domain.entities.Genre
import com.will.moviedbapp.domain.entities.Movie
import com.will.moviedbapp.domain.entities.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getTrendingMovies(): Flow<Result<List<Movie>>>
    suspend fun getMovieDetail(id: Int): Flow<Result<MovieDetail>>
    suspend fun search(query: String): Flow<Result<List<Movie>>>
    suspend fun getGenres(): Flow<Result<List<Genre>>>
    suspend fun discoverMovies(genresId: Array<Int>?): Flow<Result<List<Movie>>>
}
