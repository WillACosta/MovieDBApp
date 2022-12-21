package com.will.moviedbapp.core.usecase

import com.will.moviedbapp.core.state.Result
import kotlinx.coroutines.flow.Flow

abstract class UseCase<Param, Source> {
    abstract suspend operator fun invoke(param: Param): Flow<Result<Source>>
}
