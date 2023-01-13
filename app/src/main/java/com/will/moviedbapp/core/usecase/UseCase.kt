package com.will.moviedbapp.core.usecase

import kotlinx.coroutines.flow.Flow

abstract class UseCase<Param, Source> {
    abstract suspend operator fun invoke(param: Param): Flow<Result<Source>>
}

abstract class UseCaseWithoutParams<Source> {
    abstract suspend operator fun invoke(): Flow<Result<Source>>
}
