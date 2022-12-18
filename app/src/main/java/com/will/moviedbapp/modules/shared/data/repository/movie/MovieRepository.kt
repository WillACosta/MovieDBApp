package com.will.moviedbapp.modules.shared.data.repository.movie

import com.will.moviedbapp.core.state.StateResult
import com.will.moviedbapp.modules.home.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getTrendingMovies(): Flow<StateResult<List<Movie>>>
    suspend fun getMovie(id: Int): Flow<StateResult<Movie>>
    suspend fun search(query: String): Flow<StateResult<List<Movie>>>
}
