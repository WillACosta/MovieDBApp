package com.will.moviedbapp.domain.usecases

import com.will.moviedbapp.core.usecase.UseCase
import com.will.moviedbapp.data.repository.movie.MovieRepository
import com.will.moviedbapp.domain.entities.Movie
import kotlinx.coroutines.flow.Flow

class SearchUseCase(private val repository: MovieRepository) : UseCase<String, List<Movie>>() {
    override suspend fun invoke(param: String): Flow<Result<List<Movie>>> {
        return repository.search(param)
    }
}
