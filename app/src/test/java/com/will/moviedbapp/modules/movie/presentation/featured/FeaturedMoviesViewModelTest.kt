package com.will.moviedbapp.modules.movie.presentation.featured

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.will.moviedbapp.core.state.Result
import com.will.moviedbapp.domain.usecases.GetTrendingMoviesUseCase
import com.will.moviedbapp.resources.mocks.MockMovie
import com.will.moviedbapp.resources.utils.TestDispatcherRule
import com.will.moviedbapp.resources.utils.getOrAwaitValue
import com.will.moviedbapp.ui.viewmodels.FeaturedMoviesViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

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
    fun `should load trending movies when viewModel starts`() = runTest {
        val expected = Result.successOrEmpty(MockMovie.listMovie)

        coEvery { useCase(Unit) } returns flow {
            emit(expected)
        }

        viewModel.getTrendingMovies()

        val liveData = viewModel.moviesList
        val actual = liveData.getOrAwaitValue()

        advanceUntilIdle()

        assertEquals(
            expected,
            actual
        )
    }
}
