package com.will.moviedbapp.domain.usecases

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.will.moviedbapp.data.repository.movie.MovieRepository
import com.will.moviedbapp.resources.mocks.MockMovieData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
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
    fun `should invoke method and returns normally`() {
        val expected = Result.success(MockMovieData.movieList)

        coEvery { repository.getTrendingMovies() } returns flow {
            emit(Result.success(MockMovieData.movieList))
        }

        runBlocking {
            val flow = useCase()
            flow.test {
                assertThat(expectMostRecentItem()).isEqualTo(expected)
                coVerify { repository.getTrendingMovies() }
            }
        }
    }
}
