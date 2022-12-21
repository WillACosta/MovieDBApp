package com.will.moviedbapp.modules.movie.domain.usecase

import com.will.moviedbapp.core.state.Result
import com.will.moviedbapp.core.usecase.UseCase
import com.will.moviedbapp.modules.movie.domain.entity.MovieGenre
import com.will.moviedbapp.modules.shared.data.repository.remote.movie.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetGenresListUseCase(private val repository: MovieRepository) :
    UseCase<Unit, List<MovieGenre>>() {
    override suspend fun invoke(param: Unit): Flow<Result<List<MovieGenre>>> {
        return repository.getGenres()
    }
}
