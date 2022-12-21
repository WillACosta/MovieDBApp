package com.will.moviedbapp.modules.home.domain.usecase

import com.will.moviedbapp.modules.home.domain.usecase.SearchUseCase
import com.will.moviedbapp.modules.shared.data.repository.remote.movie.MovieRepository
import com.will.moviedbapp.resources.mocks.MockMovie
import com.will.moviedbapp.resources.mocks.MockResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlin.test.assertEquals
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

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
        val expectedState = MockResult.expectedSuccessListMovie

        coEvery { repository.search(any()) } returns expectedState.asFlow()

        runBlocking {
            val flow = useCase(searchQuery)
            val results = flow.toList()

            assertEquals(expectedState, results)
            coVerify { repository.search(any()) }
        }
    }
}
