package com.will.moviedbapp.domain.usecase

import com.will.moviedbapp.modules.shared.data.repository.remote.movie.MovieRepository
import com.will.moviedbapp.modules.movie.domain.usecase.GetTrendingMoviesUseCase
import com.will.moviedbapp.resources.mocks.MockStateResult
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
        val expectedState = MockStateResult.expectedSuccessListMovie

        coEvery { repository.getTrendingMovies() } returns expectedState.asFlow()

        runBlocking {
            val flow = useCase(Unit)
            val results = flow.toList()

            assertEquals(expectedState, results)
            coVerify { repository.getTrendingMovies() }
        }
    }
}
