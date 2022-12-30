package com.will.moviedbapp.domain.usecases

import com.will.moviedbapp.core.state.Result
import com.will.moviedbapp.data.repository.movie.MovieRepository
import com.will.moviedbapp.resources.mocks.MockMovie
import com.will.moviedbapp.resources.mocks.MockResult
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

class GetGenresListUseCaseTest {
    private lateinit var useCase: GetGenresListUseCase

    @MockK
    private lateinit var repository: MovieRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetGenresListUseCase(repository)
    }

    @Test
    fun `should call repository method and returns a list of genres`() {
        coEvery { repository.getGenres() } returns MockResult.expectedGenresListResult.asFlow()

        runBlocking {
            val flow = useCase(Unit)
            val result = flow.toList()

            assertEquals(2, result.count())
            assertEquals(Result.Loading, result[0])
            assertEquals(Result.successOrEmpty(MockMovie.genresList), result[1])
            coVerify { repository.getGenres() }
        }
    }

}
