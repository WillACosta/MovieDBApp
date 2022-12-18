package com.will.moviedbapp.domain.usecase

import com.will.moviedbapp.modules.shared.data.repository.movie.MovieRepository
import com.will.moviedbapp.modules.home.domain.use_case.GetMovieUseCase
import com.will.moviedbapp.resources.mocks.MockMovie
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
        val expectedState = MockStateResult.expectedSuccessMovie

        coEvery { repository.getMovie(any()) } returns expectedState.asFlow()

        runBlocking {
            val flow = useCase(MockMovie.movieID)
            val results = flow.toList()

            assertEquals(expectedState, results)
            coVerify { repository.getMovie(any()) }
        }
    }
}
