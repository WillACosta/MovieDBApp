package com.will.moviedbapp.modules.movie.domain.usecase

import com.will.moviedbapp.data.repository.remote.movie.MovieRepository
import com.will.moviedbapp.domain.usecases.GetMovieUseCase
import com.will.moviedbapp.resources.mocks.MockMovie
import com.will.moviedbapp.resources.mocks.MockResult
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

class GetMovieUseCaseTest {

    @MockK
    private lateinit var repository: MovieRepository
    private lateinit var useCase: GetMovieUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetMovieUseCase(repository)
    }

    @Test
    fun `should call execute method and returns normally`() {
        val expectedState = MockResult.expectedSuccessMovie

        coEvery { repository.getMovie(any()) } returns expectedState.asFlow()

        runBlocking {
            val flow = useCase(MockMovie.movieID)
            val results = flow.toList()

            assertEquals(expectedState, results)
            coVerify { repository.getMovie(any()) }
        }
    }
}

