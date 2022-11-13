package com.will.moviedbapp.presentation.view.name

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.will.moviedbapp.core.constants.AppConstants
import com.will.moviedbapp.data.repository.userPreferences.UserPreferencesRepository
import com.will.moviedbapp.presentation.view.name.NameViewModel
import com.will.moviedbapp.resources.utils.TestDispatcherRule
import com.will.moviedbapp.resources.utils.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NameViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @MockK
    private lateinit var repository: UserPreferencesRepository
    private lateinit var viewModel: NameViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = NameViewModel(repository)
    }

    @Test
    fun `should call onNameChanged and update liveData value`() {
        val expected = "Jane"

        viewModel.onNameChanged(expected)

        val liveData = viewModel.name
        val actual = liveData.getOrAwaitValue()

        assertEquals(expected, actual)
    }

    @Test
    fun `should validate if name was invalid`() {
        viewModel.onNameChanged("")

        val liveData = viewModel.error
        val actual = liveData.getOrAwaitValue()

        assertEquals(AppConstants.ValidationMessages.INVALID_NAME, actual)
    }

    @Test
    fun `should validate if name has less than three characters`() {
        viewModel.onNameChanged("Jan")

        val liveData = viewModel.error
        val actual = liveData.getOrAwaitValue()

        assertEquals(AppConstants.ValidationMessages.LESS_THAN_THREE_CHARACTERS, actual)
    }

    @Test
    fun `should submit name and call updateUserName`() {
        viewModel.onNameChanged("Jane")
        viewModel.submitName()

        coVerify { repository.updateUserName(any()) }
    }
}
