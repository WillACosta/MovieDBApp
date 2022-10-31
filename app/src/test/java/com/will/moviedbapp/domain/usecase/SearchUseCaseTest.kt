package com.will.moviedbapp.domain.usecase

import com.will.moviedbapp.data.repository.MovieRepository
import com.will.moviedbapp.resources.mocks.MockMovie
import com.will.moviedbapp.resources.mocks.MockStateResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class SearchUseCaseTest {

    @MockK
    private lateinit var repository: MovieRepository
    private lateinit var useCase: SearchUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = SearchUseCase(repository)
    }

    @Test
    fun `should invoke useCase and returns normally`() {
        val searchQuery = MockMovie.searchQuery
        val expectedState = MockStateResult.expectedSuccessListMovie

        coEvery { repository.search(any()) } returns expectedState.asFlow()

        runBlocking {
            val flow = useCase(searchQuery)
            val results = flow.toList()

            assertEquals(expectedState, results)
            coVerify { repository.search(any()) }
        }
    }

}
