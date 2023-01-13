package com.will.moviedbapp.domain.usecases

import com.will.moviedbapp.core.usecase.UseCase
import com.will.moviedbapp.data.repository.movie.MovieRepository
import com.will.moviedbapp.domain.entities.Genre
import kotlinx.coroutines.flow.Flow

class GetGenresListUseCase(private val repository: MovieRepository) :
    UseCase<Unit, List<Genre>>() {
    override suspend fun invoke(param: Unit): Flow<Result<List<Genre>>> {
        return repository.getGenres()
    }
}
