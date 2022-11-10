package com.will.moviedbapp.data.repository.userPreferences

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.will.moviedbapp.domain.model.UserPreferences
import com.will.moviedbapp.resources.utils.TestDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class CUserPreferencesRepositoryTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @MockK
    private lateinit var testDataStore: DataStore<Preferences>
    private lateinit var repository: UserPreferencesRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = CUserPreferencesRepository(testDataStore)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `should match initial state of user preferences`() {
        val expectedUserPreferences = UserPreferences("", false)
        coEvery { testDataStore.data } returns flow {
            emit(testDataStore.data.first().toPreferences())
        }

        return runBlocking {
            val flow = repository.getPreferences()
            val result = flow.toList()

            assertEquals(expectedUserPreferences, result[0])
        }
    }
}


