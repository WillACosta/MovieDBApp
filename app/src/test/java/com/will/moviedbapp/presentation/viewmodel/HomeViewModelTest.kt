package com.will.moviedbapp.presentation.viewmodel

import com.will.moviedbapp.core.state.StateResult
import com.will.moviedbapp.domain.model.Movie
import com.will.moviedbapp.domain.usecase.GetTrendingMoviesUseCase
import com.will.moviedbapp.resources.mocks.MockMovie
import com.will.moviedbapp.resources.mocks.MockStateResult
import com.will.moviedbapp.resources.utils.TestDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class HomeViewModelTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @MockK
    private lateinit var useCase: GetTrendingMoviesUseCase
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should load trending movies when viewModel starts`() = runTest {

        coEvery { useCase(Unit) } returns flow {
            emit(StateResult.loading())
            delay(1000)
            emit(StateResult.successOrEmpty(MockMovie.listMovie))
        }

        viewModel = HomeViewModel(useCase)

        val values = mutableListOf<StateResult<List<Movie>>>()
        val collectJob = launch {
            viewModel.trendingMovies.toList(values)
        }

        advanceUntilIdle()

        assertEquals(MockStateResult.expectedSuccessListMovie, values)
        collectJob.cancel()
    }
}
