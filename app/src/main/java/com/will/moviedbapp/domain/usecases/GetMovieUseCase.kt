package com.will.moviedbapp.domain.usecases

import com.will.moviedbapp.core.state.Result
import com.will.moviedbapp.core.usecase.UseCase
import com.will.moviedbapp.domain.entities.Movie
import com.will.moviedbapp.data.repository.movie.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovieUseCase(private val repository: MovieRepository) : UseCase<Int, Movie>() {
    override suspend fun invoke(param: Int): Flow<Result<Movie>> {
        return repository.getMovie(param)
    }
}
