package com.will.moviedbapp.data.datasource.movie

import com.will.moviedbapp.network.services.MovieApiService
import com.will.moviedbapp.resources.mocks.MockApiData
import com.will.moviedbapp.resources.mocks.MockResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.IOException
import kotlin.test.assertTrue

@RunWith(JUnit4::class)
class CMovieDataSourceTest {

    @MockK
    lateinit var service: MovieApiService

    private lateinit var dataSource: MovieDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        dataSource = CMovieDataSource(service)
    }

    @Test
    fun `should call getTrendingMovies and returns Result with MovieListResponse`() {
        val expected = MockResult.movieListResponseSuccess

        coEvery { service.getTrendingMovies() } returns MockApiData.movieListResponse

        runBlocking {
            val resultOrFailure = dataSource.getTrendingMovies()

            assertEquals(expected, resultOrFailure)
            coVerify { service.getTrendingMovies() }
        }
    }

    @Test
    fun `should call getTrendingMovies and returns Result with Failure`() {
        coEvery { service.getTrendingMovies() } throws IOException()

        runBlocking {
            val resultOrFailure = dataSource.getTrendingMovies()

            assertTrue(resultOrFailure.isFailure)
            coVerify { service.getTrendingMovies() }
        }
    }

    @Test
    fun `should call getGenres and returns Result with GenreListResponse`() {
        val expected = MockResult.genreListResponseSuccess

        coEvery { service.getGenres() } returns MockApiData.genreListResponse

        runBlocking {
            val response = dataSource.getGenres()
            assertEquals(expected, response)
            coVerify { service.getGenres() }
        }
    }

    @Test
    fun `should call getMovie and returns getGenres with Failure if response is unsuccessful`() {
        coEvery { service.getGenres() } throws IOException()

        runBlocking {
            val response = dataSource.getGenres()

            assertTrue(response.isFailure)
            coVerify { service.getGenres() }
        }
    }

    @Test
    fun `should call getMovie and returns Result with MovieDetailResponse`() {
        val expected = MockResult.movieDetailResponseSuccess
        val fakeID = 1

        coEvery { service.getMovieById(fakeID) } returns MockApiData.movieDetailResponse

        runBlocking {
            val response = dataSource.getMovie(fakeID)
            assertEquals(expected, response)
            coVerify { service.getMovieById(fakeID) }
        }
    }

    @Test
    fun `should call getMovie and returns an Failure if response is unsuccessful`() {
        val fakeID = 1
        coEvery { service.getMovieById(fakeID) } throws IOException()

        runBlocking {
            val response = dataSource.getMovie(fakeID)

            assertTrue(response.isFailure)
            coVerify { service.getMovieById(fakeID) }
        }
    }

    @Test
    fun `should call search and returns Result with MovieDetailResponse`() {
        val expected = MockResult.movieListResponseSuccess
        val fakeQuery = "dune"

        coEvery { service.searchMovie(fakeQuery) } returns MockApiData.movieListResponse

        runBlocking {
            val response = dataSource.search(fakeQuery)
            assertEquals(expected, response)
            coVerify { service.searchMovie(fakeQuery) }
        }
    }

    @Test
    fun `should call search and returns an Failure if response is unsuccessful`() {
        val fakeQuery = "dune"
        coEvery { service.searchMovie(fakeQuery) } throws IOException()

        runBlocking {
            val response = dataSource.search(fakeQuery)

            assertTrue(response.isFailure)
            coVerify { service.searchMovie(fakeQuery) }
        }
    }

    @Test
    fun `should call discoverMovies and returns Result with MovieListResponse`() {
        val expected = MockResult.movieListResponseSuccess

        coEvery { service.discoverMovies(any()) } returns MockApiData.movieListResponse

        runBlocking {
            val response = dataSource.discoverMovies(null)

            assertEquals(expected, response)
            coVerify { service.discoverMovies(any()) }
        }
    }

    @Test
    fun `should call discoverMovies and returns an Failure if response is unsuccessful`() {
        coEvery { service.discoverMovies(any()) } throws IOException()

        runBlocking {
            val response = dataSource.discoverMovies(null)

            assertTrue(response.isFailure)
            coVerify { service.discoverMovies(any()) }
        }
    }

}
