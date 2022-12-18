package com.will.moviedbapp.modules.home.domain.use_case

import com.will.moviedbapp.core.state.StateResult
import com.will.moviedbapp.core.usecase.UseCase
import com.will.moviedbapp.modules.shared.data.repository.movie.MovieRepository
import com.will.moviedbapp.modules.home.domain.model.Movie
import kotlinx.coroutines.flow.Flow

class GetMovieUseCase(private val repository: MovieRepository) : UseCase<Int, Movie>() {
    override suspend fun invoke(param: Int): Flow<StateResult<Movie>> {
        return repository.getMovie(param)
    }
}
