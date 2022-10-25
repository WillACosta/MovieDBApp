package com.will.moviedbapp.domain.usecase

import com.will.moviedbapp.core.state.StateResult
import com.will.moviedbapp.core.usecase.UseCase
import com.will.moviedbapp.data.repository.MovieRepository
import com.will.moviedbapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow

class SearchUseCase(private val repository: MovieRepository) : UseCase<String, List<Movie>>() {
    override suspend fun invoke(param: String): Flow<StateResult<List<Movie>>> {
        return repository.search(param)
    }
}
