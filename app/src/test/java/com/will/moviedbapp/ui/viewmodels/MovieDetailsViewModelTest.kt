package com.will.moviedbapp.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.will.moviedbapp.core.state.Result
import com.will.moviedbapp.domain.usecases.GetMovieUseCase
import com.will.moviedbapp.resources.mocks.MockMovie
import com.will.moviedbapp.resources.utils.TestDispatcherRule
import com.will.moviedbapp.resources.utils.getOrAwaitValue
import com.will.moviedbapp.ui.viewmodels.MovieDetailsViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlin.test.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieDetailsViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @MockK
    private lateinit var useCase: GetMovieUseCase
    private lateinit var viewModel: MovieDetailsViewModel

    private val fakeMovieID = "1"

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = MovieDetailsViewModel(useCase)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should get movie by given id`() {
        val expected = Result.success(MockMovie.movie)

        coEvery { useCase(any()) } returns flow { emit(expected) }

        viewModel.getMovie(fakeMovieID)

        val liveData = viewModel.movie
        val actual = liveData.getOrAwaitValue()

        assertEquals(expected, actual)
    }
}
