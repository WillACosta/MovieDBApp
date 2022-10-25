package com.will.moviedbapp.domain.usecase

import com.will.moviedbapp.core.state.StateResult
import com.will.moviedbapp.data.repository.MovieRepository
import com.will.moviedbapp.domain.model.Movie
import com.will.moviedbapp.resources.mocks.MockMovie
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class GetTrendingMoviesUseCaseTest {

    @MockK
    private lateinit var repository: MovieRepository
    private lateinit var useCase: GetTrendingMoviesUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetTrendingMoviesUseCase(repository)
    }

    @Test
    fun `should call execute method and returns normally`() {
        val listMovie = MockMovie.listMovie
        val expectedState = listOf(
            StateResult.Loading,
            StateResult.Success<List<Movie>>(listMovie),
        )

        coEvery { repository.getTrendingMovies() } returns expectedState.asFlow()

        runBlocking {
            val flow = useCase(Unit)
            val results = flow.toList()

            assertEquals(expectedState, results)
            coVerify { repository.getTrendingMovies() }
        }

    }

}
