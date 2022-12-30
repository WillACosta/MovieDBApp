package com.will.moviedbapp.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.will.moviedbapp.data.repository.user_preferences.UserPreferencesRepository
import com.will.moviedbapp.ui.viewmodels.PreferencesViewModel
import com.will.moviedbapp.resources.mocks.MockPreferences
import com.will.moviedbapp.resources.utils.TestDispatcherRule
import com.will.moviedbapp.resources.utils.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PreferencesViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @MockK
    private lateinit var repository: UserPreferencesRepository
    private lateinit var viewModel: PreferencesViewModel

    private val expected = MockPreferences.userPreferences

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `should call getPreferences in viewModel and return a UserPreferences in liveData`() {
        coEvery { repository.getPreferences() } returns flow {
            emit(expected)
        }

        viewModel = PreferencesViewModel(repository)

        val liveData = viewModel.userPreferences
        val actual = liveData.getOrAwaitValue()

        assertEquals(expected, actual)
        coVerify { repository.getPreferences() }
    }
}
