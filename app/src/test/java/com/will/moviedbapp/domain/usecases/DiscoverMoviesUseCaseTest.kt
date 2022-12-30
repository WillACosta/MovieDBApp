package com.will.moviedbapp.domain.usecases

import com.will.moviedbapp.data.repository.movie.MovieRepository
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

class DiscoverMoviesUseCaseTest {
    @MockK
    private lateinit var repository: MovieRepository
    private lateinit var useCase: DiscoverMoviesUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = DiscoverMoviesUseCase(repository)
    }

    @Test
    fun `should call invoke method and returns normally`() {
        val expectedState = MockResult.expectedSuccessListMovie

        coEvery { repository.discoverMovies(any()) } returns expectedState.asFlow()

        runBlocking {
            val flow = useCase(MockMovie.genresIdList)
            val results = flow.toList()

            assertEquals(expectedState, results)
            coVerify { repository.discoverMovies(any()) }
        }
    }
}
