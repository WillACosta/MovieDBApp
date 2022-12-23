package com.will.moviedbapp.modules.movie.domain.usecase

import com.will.moviedbapp.core.state.Result
import com.will.moviedbapp.core.usecase.UseCase
import com.will.moviedbapp.modules.movie.domain.entity.Movie
import com.will.moviedbapp.modules.shared.data.repository.remote.movie.MovieRepository
import kotlinx.coroutines.flow.Flow

class DiscoverMoviesUseCase(private val repository: MovieRepository) :
    UseCase<Array<Int>?, List<Movie>>() {
    override suspend fun invoke(param: Array<Int>?): Flow<Result<List<Movie>>> {
        return repository.discoverMovies(param)
    }
}
