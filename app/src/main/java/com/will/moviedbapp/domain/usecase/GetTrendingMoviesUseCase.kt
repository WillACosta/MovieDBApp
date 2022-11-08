package com.will.moviedbapp.domain.usecase

import com.will.moviedbapp.core.state.StateResult
import com.will.moviedbapp.core.usecase.UseCase
import com.will.moviedbapp.data.repository.movie.MovieRepository
import com.will.moviedbapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow

class GetTrendingMoviesUseCase(private val repository: MovieRepository) : UseCase<Unit, List<Movie>>() {
    override suspend fun invoke(param: Unit): Flow<StateResult<List<Movie>>> {
        return repository.getTrendingMovies()
    }
}
