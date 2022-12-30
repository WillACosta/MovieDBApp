package com.will.moviedbapp.domain.usecases

import com.will.moviedbapp.core.state.Result
import com.will.moviedbapp.core.usecase.UseCase
import com.will.moviedbapp.domain.entities.MovieGenre
import com.will.moviedbapp.data.repository.remote.movie.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetGenresListUseCase(private val repository: MovieRepository) :
    UseCase<Unit, List<MovieGenre>>() {
    override suspend fun invoke(param: Unit): Flow<Result<List<MovieGenre>>> {
        return repository.getGenres()
    }
}
