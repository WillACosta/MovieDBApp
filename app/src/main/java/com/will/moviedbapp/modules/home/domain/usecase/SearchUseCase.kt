package com.will.moviedbapp.modules.home.domain.usecase

import com.will.moviedbapp.core.state.StateResult
import com.will.moviedbapp.core.usecase.UseCase
import com.will.moviedbapp.modules.movie.domain.entity.Movie
import com.will.moviedbapp.modules.shared.data.repository.remote.movie.MovieRepository
import kotlinx.coroutines.flow.Flow

class SearchUseCase(private val repository: MovieRepository) : UseCase<String, List<Movie>>() {
    override suspend fun invoke(param: String): Flow<StateResult<List<Movie>>> {
        return repository.search(param)
    }
}
