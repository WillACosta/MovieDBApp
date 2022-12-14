package com.will.moviedbapp.domain.usecase

import com.will.moviedbapp.core.state.StateResult
import com.will.moviedbapp.core.usecase.UseCase
import com.will.moviedbapp.domain.model.MovieGenre
import kotlinx.coroutines.flow.Flow

class GetGenresList : UseCase<Unit, List<MovieGenre>>() {
    override suspend fun invoke(param: Unit): Flow<StateResult<List<MovieGenre>>> {
        TODO("Not yet implemented")
    }
}
