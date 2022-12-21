package com.will.moviedbapp.modules.shared.data.repository.remote.movie

import com.will.moviedbapp.core.state.Result
import com.will.moviedbapp.modules.movie.domain.entity.Movie
import com.will.moviedbapp.modules.movie.domain.entity.MovieGenre
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getTrendingMovies(): Flow<Result<List<Movie>>>
    suspend fun getMovie(id: Int): Flow<Result<Movie>>
    suspend fun search(query: String): Flow<Result<List<Movie>>>
    suspend fun getGenres(): Flow<Result<List<MovieGenre>>>
}
