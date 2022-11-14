@file:OptIn(ExperimentalCoroutinesApi::class)

package com.will.moviedbapp.presentation.view.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.will.moviedbapp.core.state.StateResult
import com.will.moviedbapp.domain.model.Movie
import com.will.moviedbapp.domain.usecase.GetTrendingMoviesUseCase
import com.will.moviedbapp.domain.usecase.SearchUseCase
import com.will.moviedbapp.presentation.model.HomeAction
import com.will.moviedbapp.resources.mocks.MockMovie
import com.will.moviedbapp.resources.mocks.MockStateResult
import com.will.moviedbapp.resources.utils.TestDispatcherRule
import com.will.moviedbapp.resources.utils.getOrAwaitValue
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
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @MockK
    private lateinit var useCase: GetTrendingMoviesUseCase

    @MockK
    private lateinit var searchUseCase: SearchUseCase

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = HomeViewModel(useCase, searchUseCase)
    }

    @Test
    fun `should load trending movies when viewModel starts`() = runTest {

        coEvery { useCase(Unit) } returns flow {
            emit(StateResult.loading())
            delay(200)
            emit(StateResult.successOrEmpty(MockMovie.listMovie))
        }

        viewModel.handle(HomeAction.LoadTrendingMovies)

        val values = mutableListOf<StateResult<List<Movie>>>()
        val collectJob = launch {
            viewModel.trendingMovies.toList(values)
        }

        advanceUntilIdle()

        assertEquals(MockStateResult.expectedSuccessListMovie, values)
        collectJob.cancel()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should handle query text and load list of movies`() {
        runTest {
            val expected = StateResult.Success(MockMovie.listMovie)

            coEvery { searchUseCase(any()) } returns flow {
                emit(StateResult.Success(MockMovie.listMovie))
            }

            viewModel.handle(HomeAction.SearchMovies(MockMovie.searchQuery))
            advanceUntilIdle()

            val liveData = viewModel.movies
            val actual = liveData.getOrAwaitValue()

            assertEquals(expected, actual)
        }
    }

    @Test
    fun `should push navigate event with movie when action is ViewMovieDetails`() {
        val expected = MockMovie.movie

        viewModel.handle(HomeAction.ViewMovieDetails(expected))

        val liveData = viewModel.navigateEvent
        val actual = liveData.getOrAwaitValue()

        assertEquals(expected, actual)
    }
}

