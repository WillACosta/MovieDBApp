package com.will.moviedbapp.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.will.moviedbapp.core.model.ThemeConfig
import com.will.moviedbapp.core.model.UserData
import com.will.moviedbapp.data.repository.UserPreferencesRepository
import com.will.moviedbapp.resources.mocks.MockPreferences
import com.will.moviedbapp.resources.utils.TestDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class WelcomeViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @MockK
    private lateinit var userPreferencesRepository: UserPreferencesRepository
    private lateinit var viewModel: WelcomeViewModel

    private val fakeUserData = MockPreferences.userData

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        stubRepositoryResponse()

        viewModel = WelcomeViewModel(userPreferencesRepository)
    }

    private fun stubRepositoryResponse() {
        coEvery { userPreferencesRepository.userData } returns flow {
            emit(fakeUserData)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should collect correct userPreferences data and map to WelcomeUiStateData`() {
        val welcomeUIStateData = WelcomeUiStateData(
            fakeUserData.name,
            fakeUserData.shouldHideWelcome
        )

        runBlocking {
            viewModel.userPreferences.test {
                assertThat(awaitItem()).isEqualTo(welcomeUIStateData)
                assertThat(cancelAndConsumeRemainingEvents())
            }
        }
    }

    @Test
    fun `should call hideWelcome correctly`() {
        viewModel.hideWelcome()
        coVerify { userPreferencesRepository.setShouldHideWelcome(true) }
    }

}
