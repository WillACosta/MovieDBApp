package com.will.moviedbapp.modules.movie.domain.usecase

import com.will.moviedbapp.domain.usecases.GetTrendingMoviesUseCase
import com.will.moviedbapp.data.repository.remote.movie.MovieRepository
import com.will.moviedbapp.resources.mocks.MockResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlin.test.assertEquals
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

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
        val expectedState = MockResult.expectedSuccessListMovie

        coEvery { repository.getTrendingMovies() } returns expectedState.asFlow()

        runBlocking {
            val flow = useCase(Unit)
            val results = flow.toList()

            assertEquals(expectedState, results)
            coVerify { repository.getTrendingMovies() }
        }
    }
}
