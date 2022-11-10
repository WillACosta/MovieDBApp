package com.will.moviedbapp.resources.mocks

import com.will.moviedbapp.core.errors.RemoteDataSourceException
import com.will.moviedbapp.core.state.StateResult

object MockStateResult {

    private val listMovie = MockMovie.listMovie
    val expectedSuccessListMovie = listOf(
        StateResult.Loading,
        StateResult.Success(listMovie),
    )

    private val movie = MockMovie.movie
    val expectedSuccessMovie = listOf(
        StateResult.Loading,
        StateResult.Success(movie),
    )

    val expectedEmptyStateList = listOf(
        StateResult.Loading,
        StateResult.Empty
    )

    val expectedErrorStateList = listOf(
        StateResult.Loading,
        StateResult.Error(RemoteDataSourceException())
    )

}
