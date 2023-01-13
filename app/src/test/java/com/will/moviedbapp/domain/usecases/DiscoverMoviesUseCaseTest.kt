package com.will.moviedbapp.domain.usecases

import com.will.moviedbapp.data.repository.movie.MovieRepository
import com.will.moviedbapp.resources.mocks.MockMovieData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

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
    fun `should invoke method and returns normally`() {
        val successResult = Result.success(MockMovieData.movieList)
        val expected = listOf(successResult)
        val genreIds = arrayOf(1, 2)

        coEvery { repository.discoverMovies(any()) } returns flow {
            emit(successResult)
        }

        runBlocking {
            val flow = useCase(genreIds)
            val results = flow.toList()

            kotlin.test.assertEquals(expected, results)
            coVerify { repository.discoverMovies(any()) }
        }
    }
}
