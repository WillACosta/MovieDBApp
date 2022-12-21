package com.will.moviedbapp.modules.movie.domain.usecase

import com.will.moviedbapp.core.state.Result
import com.will.moviedbapp.core.usecase.UseCase
import com.will.moviedbapp.modules.movie.domain.entity.Movie
import com.will.moviedbapp.modules.shared.data.repository.remote.movie.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovieUseCase(private val repository: MovieRepository) : UseCase<Int, Movie>() {
    override suspend fun invoke(param: Int): Flow<Result<Movie>> {
        return repository.getMovie(param)
    }
}
