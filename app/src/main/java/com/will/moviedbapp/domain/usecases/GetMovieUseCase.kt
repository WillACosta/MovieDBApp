package com.will.moviedbapp.domain.usecases

import com.will.moviedbapp.core.usecase.UseCase
import com.will.moviedbapp.data.repository.movie.MovieRepository
import com.will.moviedbapp.domain.entities.MovieDetail
import kotlinx.coroutines.flow.Flow

class GetMovieUseCase(private val repository: MovieRepository) : UseCase<Int, MovieDetail>() {
    override suspend fun invoke(param: Int): Flow<Result<MovieDetail>> {
        return repository.getMovieDetail(param)
    }
}
