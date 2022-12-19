package com.will.moviedbapp.modules.movie.domain.usecase

import com.will.moviedbapp.core.state.StateResult
import com.will.moviedbapp.core.usecase.UseCase
import com.will.moviedbapp.modules.movie.domain.entity.Movie
import com.will.moviedbapp.modules.shared.data.repository.remote.movie.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetTrendingMoviesUseCase(private val repository: MovieRepository) :
    UseCase<Unit, List<Movie>>() {
    override suspend fun invoke(param: Unit): Flow<StateResult<List<Movie>>> {
        return repository.getTrendingMovies()
    }
}
