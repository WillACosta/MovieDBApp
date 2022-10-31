package com.will.moviedbapp.data.repository

import com.will.moviedbapp.core.errors.RemoteDataSourceException
import com.will.moviedbapp.core.state.StateResult
import com.will.moviedbapp.data.datasource.movieDb.MovieDBRemoteDataSource
import com.will.moviedbapp.resources.mocks.MockMovie
import com.will.moviedbapp.resources.mocks.MockStateResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CMovieRepositoryTest {

    @MockK
    lateinit var remote: MovieDBRemoteDataSource
    private lateinit var repository: MovieRepository

    private val expectedErrorStateList = MockStateResult.expectedErrorStateList
    private val expectedEmptyStateList = MockStateResult.expectedEmptyStateList

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = CMovieRepository(remote)
    }

    @Test
    fun `should call getTrendingMovies and returns a list state of StateResult with Success`() {
        val listMovie = MockMovie.listMovie
        val expectedStates = MockStateResult.expectedSuccessListMovie

        coEvery { remote.getTrendingMovies() } returns listMovie

        runBlocking {
            val flow = repository.getTrendingMovies()
            val results = flow.toList()

            assertEquals(2, results.count())
            assertEquals(expectedStates, results)
        }
    }

    @Test
    fun `should call getTrendingMovies and returns last StateResult as a Empty if remote returns an empty list`() {
        coEvery { remote.getTrendingMovies() } returns emptyList()

        runBlocking {
            val flow = repository.getTrendingMovies()
            val results = flow.toList()

            assertEquals(2, results.count())
            assertEquals(expectedEmptyStateList, results)
        }
    }

    @Test
    fun `should call getTrendingMovies and returns last StateResult as a Error if repository calls was unsuccessfully`() {
        coEvery { remote.getTrendingMovies() } throws RemoteDataSourceException()

        runBlocking {
            val flow = repository.getTrendingMovies()
            val results = flow.toList()

            assertEquals(2, results.count())
            assertEquals(expectedErrorStateList[0], results[0])
            assert(expectedErrorStateList[1] is StateResult.Error)
        }
    }

    @Test
    fun `should call getMovie and returns a list state of StateResult with Success`() {
        val movie = MockMovie.movie
        val expectedStates = MockStateResult.expectedSuccessListMovie

        coEvery { remote.getMovie(any()) } returns movie

        runBlocking {
            val flow = repository.getMovie(MockMovie.movieID)
            val results = flow.toList()

            assertEquals(2, results.count())
            assertEquals(expectedStates, results)
            coVerify { remote.getMovie(any()) }
        }
    }

    @Test
    fun `should call getMovie and returns last StateResult as a Error if repository calls was unsuccessfully`() {
        coEvery { remote.getMovie(any()) } throws RemoteDataSourceException()

        runBlocking {
            val flow = repository.getMovie(MockMovie.movieID)
            val results = flow.toList()

            assertEquals(2, results.count())
            assertEquals(expectedErrorStateList[0], results[0])
            assert(expectedErrorStateList[1] is StateResult.Error)
        }
    }

    @Test
    fun `should call search and returns a list state of StateResult with Success`() {
        coEvery { remote.search(any()) } returns MockMovie.listMovie

        val expectedStates = MockStateResult.expectedSuccessMovie

        runBlocking {
            val flow = repository.search(MockMovie.searchQuery)
            val results = flow.toList()

            assertEquals(2, results.count())
            assertEquals(expectedStates, results)
        }
    }

    @Test
    fun `should call search and returns last StateResult as a Empty if remote returns an empty list`() {
        coEvery { remote.search(any()) } returns emptyList()

        runBlocking {
            val flow = repository.search(MockMovie.searchQuery)
            val results = flow.toList()

            assertEquals(2, results.count())
            assertEquals(expectedEmptyStateList, results)
        }
    }

    @Test
    fun `should call search and returns last StateResult as a Error if repository calls was unsuccessfully`() {
        coEvery { remote.search(any()) } throws RemoteDataSourceException()

        runBlocking {
            val flow = repository.search(MockMovie.searchQuery)
            val results = flow.toList()

            assertEquals(2, results.count())
            assertEquals(expectedErrorStateList[0], results[0])
            assert(expectedErrorStateList[1] is StateResult.Error)
        }
    }

}
