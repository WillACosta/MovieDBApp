package com.will.moviedbapp.modules.movie.domain.usecase

import com.will.moviedbapp.core.state.StateResult
import com.will.moviedbapp.core.usecase.UseCase
import com.will.moviedbapp.modules.movie.domain.entity.MovieGenre
import kotlinx.coroutines.flow.Flow

class GetGenresList : UseCase<Unit, List<MovieGenre>>() {
    override suspend fun invoke(param: Unit): Flow<StateResult<List<MovieGenre>>> {
        TODO("Not yet implemented")
    }
}
