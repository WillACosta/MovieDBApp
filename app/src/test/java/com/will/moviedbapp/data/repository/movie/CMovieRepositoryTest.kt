package com.will.moviedbapp.data.repository.movie

import com.will.moviedbapp.data.datasource.movie.MovieDataSource
import com.will.moviedbapp.resources.mocks.MockMovieData
import com.will.moviedbapp.resources.mocks.MockResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.io.IOException
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CMovieRepositoryTest {

    @MockK
    lateinit var remote: MovieDataSource
    private lateinit var repository: MovieRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = CMovieRepository(remote)
    }

    @Test
    fun `should call getTrendingMovies and emit mappedValue with Success`() {
        val emittedValues = listOf(
            Result.success(MockMovieData.movieList),
        )

        coEvery { remote.getTrendingMovies() } returns MockResult.movieListResponseSuccess

        runBlocking {
            val flow = repository.getTrendingMovies()
            val results = flow.toList()

            assertEquals(1, results.count())
            assertEquals(emittedValues, results)
        }
    }

    @Test
    fun `should call getTrendingMovies and emit an Failure`() {
        coEvery { remote.getTrendingMovies() } returns Result.failure(IOException())

        runBlocking {
            val flow = repository.getTrendingMovies()
            val results = flow.toList()

            assertEquals(1, results.count())
            assertTrue(results[0].isFailure)
        }
    }

    @Test
    fun `should call getMovie and emit mappedValue with Success`() {
        val emittedValues = listOf(
            Result.success(MockMovieData.movieDetail)
        )

        coEvery { remote.getMovie(any()) } returns MockResult.movieDetailResponseSuccess

        runBlocking {
            val flow = repository.getMovieDetail(1)
            val results = flow.toList()

            assertEquals(1, results.count())
            assertEquals(emittedValues, results)
            coVerify { remote.getMovie(any()) }
        }
    }

    @Test
    fun `should call getMovieDetail and emit an Failure`() {
        coEvery { remote.getMovie(any()) } returns Result.failure(IOException())

        runBlocking {
            val flow = repository.getMovieDetail(1)
            val results = flow.toList()

            assertEquals(1, results.count())
            assertTrue(results[0].isFailure)
        }
    }

    @Test
    fun `should call search and emit mappedValue with Success`() {
        val query = "dune"
        val emittedValues = listOf(
            Result.success(MockMovieData.movieList)
        )

        coEvery { remote.search(any()) } returns MockResult.movieListResponseSuccess

        runBlocking {
            val flow = repository.search(query)
            val results = flow.toList()

            assertEquals(1, results.count())
            assertEquals(emittedValues, results)
            coVerify { remote.search(any()) }
        }
    }

    @Test
    fun `should call search and emit an Failure`() {
        val query = "dune"

        coEvery { remote.search(any()) } returns Result.failure(IOException())

        runBlocking {
            val flow = repository.search(query)
            val results = flow.toList()

            assertEquals(1, results.count())
            assertTrue(results[0].isFailure)
        }
    }

    @Test
    fun `should call getGenres and emit mappedValue with Success`() {
        val emittedValues = listOf(
            Result.success(MockMovieData.genreList),
        )

        coEvery { remote.getGenres() } returns MockResult.genreListResponseSuccess

        runBlocking {
            val flow = repository.getGenres()
            val results = flow.toList()

            assertEquals(1, results.count())
            assertEquals(emittedValues, results)
            coVerify { remote.getGenres() }
        }
    }

    @Test
    fun `should call getGenres and emit an Failure`() {
        coEvery { remote.getGenres() } returns Result.failure(IOException())

        runBlocking {
            val flow = repository.getGenres()
            val results = flow.toList()

            assertEquals(1, results.count())
            assertTrue(results[0].isFailure)
        }
    }

    @Test
    fun `should call discoverMovies and emit mappedValue with Success`() {
        val fakeIds = arrayOf(1, 2, 3)
        val emittedValues = listOf(
            Result.success(MockMovieData.movieList),
        )

        coEvery { remote.discoverMovies(any()) } returns MockResult.movieListResponseSuccess

        runBlocking {
            val flow = repository.discoverMovies(fakeIds)
            val results = flow.toList()

            assertEquals(1, results.count())
            assertEquals(emittedValues, results)
            coVerify { remote.discoverMovies(any()) }
        }
    }

    @Test
    fun `should call discoverMovies and emit an Failure`() {
        val fakeIds = arrayOf(1, 2, 3)

        coEvery { remote.discoverMovies(any()) } returns Result.failure(IOException())

        runBlocking {
            val flow = repository.discoverMovies(fakeIds)
            val results = flow.toList()

            assertEquals(1, results.count())
            assertTrue(results[0].isFailure)
        }
    }

}
