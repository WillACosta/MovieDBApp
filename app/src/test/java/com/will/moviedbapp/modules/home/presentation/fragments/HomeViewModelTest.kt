package com.will.moviedbapp.modules.home.presentation.fragments

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.will.moviedbapp.core.state.StateResult
import com.will.moviedbapp.modules.home.domain.usecase.SearchUseCase
import com.will.moviedbapp.resources.mocks.MockMovie
import com.will.moviedbapp.resources.utils.TestDispatcherRule
import com.will.moviedbapp.resources.utils.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
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
    private lateinit var searchUseCase: SearchUseCase

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = HomeViewModel(searchUseCase)
    }


    @Test
    fun `should handle query text and load list of movies`() {
        val expected = StateResult.Success(MockMovie.listMovie)

        coEvery { searchUseCase(any()) } returns flow {
            emit(StateResult.Success(MockMovie.listMovie))
        }

        viewModel.searchMovies(MockMovie.searchQuery)

        val liveData = viewModel.moviesResultList
        val actual = liveData.getOrAwaitValue()

        assertEquals(expected, actual)
    }
}

