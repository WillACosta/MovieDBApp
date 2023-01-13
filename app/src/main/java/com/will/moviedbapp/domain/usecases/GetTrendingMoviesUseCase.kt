package com.will.moviedbapp.domain.usecases

import com.will.moviedbapp.core.usecase.UseCaseWithoutParams
import com.will.moviedbapp.data.repository.movie.MovieRepository
import com.will.moviedbapp.domain.entities.Movie
import kotlinx.coroutines.flow.Flow

class GetTrendingMoviesUseCase(private val repository: MovieRepository) :
    UseCaseWithoutParams<List<Movie>>() {
    override suspend fun invoke(): Flow<Result<List<Movie>>> {
        return repository.getTrendingMovies()
    }
}
