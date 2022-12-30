package com.will.moviedbapp.domain.usecases

import com.will.moviedbapp.core.state.Result
import com.will.moviedbapp.core.usecase.UseCase
import com.will.moviedbapp.domain.entities.Movie
import com.will.moviedbapp.data.repository.movie.MovieRepository
import kotlinx.coroutines.flow.Flow

class DiscoverMoviesUseCase(private val repository: MovieRepository) :
    UseCase<Array<Int>?, List<Movie>>() {
    override suspend fun invoke(param: Array<Int>?): Flow<Result<List<Movie>>> {
        return repository.discoverMovies(param)
    }
}
