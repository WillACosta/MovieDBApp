package com.will.moviedbapp.presentation.view.welcome

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.will.moviedbapp.data.repository.userPreferences.UserPreferencesRepository
import com.will.moviedbapp.domain.model.UserPreferences
import com.will.moviedbapp.presentation.view.welcome.WelcomeViewModel
import com.will.moviedbapp.resources.utils.TestDispatcherRule
import com.will.moviedbapp.resources.utils.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class WelcomeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @MockK
    private lateinit var repository: UserPreferencesRepository
    private lateinit var viewModel: WelcomeViewModel

    private val expected = UserPreferences("Jane", false)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        stubRepositoryResponse()

        viewModel = WelcomeViewModel(repository)
    }

    private fun stubRepositoryResponse() {
        coEvery { repository.getPreferences() } returns flow {
            emit(expected)
        }
    }

    @Test
    fun `should get UserPreferences data when viewModel init`() {
        val liveData = viewModel.userPreferences
        val actual = liveData.getOrAwaitValue()

        assertEquals(expected, actual)
    }

    @Test
    fun `should call updateNotFirsAccess and handle first user access`() {
        viewModel.handleFirstAccess()
        coVerify { repository.updateNotFirsAccess(any()) }
    }
}
