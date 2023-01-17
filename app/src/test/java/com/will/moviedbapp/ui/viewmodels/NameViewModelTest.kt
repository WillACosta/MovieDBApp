package com.will.moviedbapp.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.will.moviedbapp.core.constants.AppConstants
import com.will.moviedbapp.data.repository.UserPreferencesRepository
import com.will.moviedbapp.resources.utils.TestDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NameViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @MockK
    private lateinit var userPreferencesRepository: UserPreferencesRepository

    private lateinit var viewModel: NameViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = NameViewModel(userPreferencesRepository)
    }

    @Test
    fun `should update userName and validate correctly`() {

        runBlocking {
            viewModel.nameError.test {
                viewModel.onNameChanged("")
                assertThat(awaitItem()).isEqualTo(AppConstants.ValidationMessages.INVALID_NAME)
                cancelAndConsumeRemainingEvents()
            }
        }
    }

    @Test
    fun `should call updateUserName repository method`() {
        runBlocking {
            viewModel.onNameChanged("User")

            viewModel.nameError.test {
                assertThat(awaitItem()).isNull()
                cancelAndConsumeRemainingEvents()
            }

            viewModel.submitName()

            coVerify { userPreferencesRepository.updateUserName(any()) }
        }
    }

}
