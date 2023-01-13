package com.will.moviedbapp.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.will.moviedbapp.core.state.UiState
import com.will.moviedbapp.domain.usecases.GetTrendingMoviesUseCase
import com.will.moviedbapp.resources.mocks.MockMovieData
import com.will.moviedbapp.resources.utils.TestDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException
import kotlin.test.assertTrue

class FeaturedMoviesViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @MockK
    private lateinit var useCase: GetTrendingMoviesUseCase

    private lateinit var viewModel: FeaturedMoviesViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = FeaturedMoviesViewModel(useCase)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should call getTrendingMovies and emit values correctly`() = runBlocking {

        coEvery { useCase() } returns flow {
            emit(Result.success(MockMovieData.movieList))
        }

        viewModel.uiState.test {
            viewModel.getTrendingMovies()

            assertThat(awaitItem()).isEqualTo(UiState.Loading)
            assertThat(awaitItem()).isEqualTo(UiState.Success(MockMovieData.movieList))
            assertThat(cancelAndConsumeRemainingEvents().size).isEqualTo(0)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should call getTrendingMovies and emit an Failure`() {
        coEvery { useCase() } returns flow {
            emit(Result.failure(IOException()))
        }

        runBlocking {
            viewModel.uiState.test {
                viewModel.getTrendingMovies()

                assertThat(awaitItem()).isEqualTo(UiState.Loading)
                assertTrue { awaitItem() is UiState.Failure }
            }
        }
    }
}
