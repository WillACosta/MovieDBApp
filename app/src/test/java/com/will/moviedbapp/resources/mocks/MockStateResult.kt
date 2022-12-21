package com.will.moviedbapp.resources.mocks

import com.will.moviedbapp.core.errors.RemoteDataSourceException
import com.will.moviedbapp.core.state.Result

object MockResult {

    val expectedSuccessListMovie = listOf(
        Result.Loading,
        Result.Success(MockMovie.listMovie),
    )

    private val movie = MockMovie.movie
    val expectedSuccessMovie = listOf(
        Result.Loading,
        Result.Success(movie),
    )

    val expectedEmptyStateList = listOf(
        Result.Loading,
        Result.Empty
    )

    val expectedErrorStateList = listOf(
        Result.Loading,
        Result.Error(RemoteDataSourceException())
    )

    val expectedGenresListResult = listOf(
        Result.loading(),
        Result.successOrEmpty(MockMovie.genresList)
    )
}
