package com.will.moviedbapp.modules.shared.data.datasource.remote.movie

import com.will.moviedbapp.core.errors.RemoteDataSourceException
import com.will.moviedbapp.modules.shared.data.services.MovieDBService
import com.will.moviedbapp.resources.mocks.MockMovie
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

@RunWith(JUnit4::class)
class CMovieDBRemoteDataSourceTest {

    @MockK
    lateinit var service: MovieDBService

    private lateinit var dataSource: MovieDBRemoteDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        dataSource = CMovieDBRemoteDataSource(service)
    }

    @Test
    fun `should call getTrendingMovies to service and returns a list of Movie`() {
        val expected = MockMovie.paginatedResponse

        coEvery { service.getTrendingMovies() } returns expected

        runBlocking {
            val response = dataSource.getTrendingMovies()

            assertEquals(expected.results, response)
            coVerify { service.getTrendingMovies() }
        }
    }

    @Test(expected = RemoteDataSourceException::class)
    fun `should throws a RemoteDataSourceException if response is unsuccessfully`() {
        coEvery { service.getTrendingMovies() } throws RemoteDataSourceException()

        runBlocking {
            dataSource.getTrendingMovies()
            coVerify { service.getTrendingMovies() }
        }
    }

    @Test
    fun `should call getGenres to service and returns a list of MovieGenre`() {
        val expected = MockMovie.genresResponse

        coEvery { service.getGenres() } returns expected

        runBlocking {
            val actual = dataSource.getGenres()

            assertEquals(actual, expected)
            coVerify { service.getGenres() }
        }
    }

    @Test(expected = RemoteDataSourceException::class)
    fun `should throws a RemoteDataSourceException if response in getGenres is unsuccessfully`() {
        coEvery { service.getGenres() } throws RemoteDataSourceException()

        runBlocking {
            dataSource.getGenres()
            coVerify { service.getGenres() }
        }
    }

    @Test
    fun `should call getMovie to service and returns a Movie`() {
        val expected = MockMovie.movie
        val movieID = MockMovie.movieID

        coEvery { service.getMovieById(movieID) } returns expected

        runBlocking {
            val response = dataSource.getMovie(movieID)

            assertEquals(expected, response)
            coVerify { service.getMovieById(any()) }
        }
    }

    @Test(expected = RemoteDataSourceException::class)
    fun `should throws a RemoteDataSourceException if response in getMovie is unsuccessfully`() {
        val movieID = MockMovie.movieID

        coEvery { service.getMovieById(movieID) } throws RemoteDataSourceException()

        runBlocking {
            dataSource.getMovie(movieID)
            coVerify { service.getMovieById(any()) }
        }
    }

    @Test
    fun `should call search to service and returns a Movie`() {
        val query = MockMovie.searchQuery
        val expected = MockMovie.paginatedResponse

        coEvery { service.searchMovie(query) } returns expected

        runBlocking {
            val response = dataSource.search(query)

            assertEquals(expected.results, response)
            coVerify { service.searchMovie(any()) }
        }
    }

    @Test(expected = RemoteDataSourceException::class)
    fun `should throws a RemoteDataSourceException if response in search is unsuccessfully`() {
        val query = MockMovie.searchQuery

        coEvery { service.searchMovie(query) } throws RemoteDataSourceException()

        runBlocking {
            dataSource.search(query)
            coVerify { service.searchMovie(any()) }
        }
    }

    @Test
    fun `should call discoverMovies to service and returns a list of MovieGenre`() {
        val expected = MockMovie.paginatedResponse

        coEvery { service.discoverMovies(MockMovie.genresIdList) } returns expected

        runBlocking {
            val actual = dataSource.discoverMovies(MockMovie.genresIdList)

            assertEquals(actual, expected.results)
            coVerify { service.discoverMovies(MockMovie.genresIdList) }
        }
    }

    @Test(expected = RemoteDataSourceException::class)
    fun `should throws a RemoteDataSourceException if response in discoverMovies is unsuccessfully`() {
        coEvery { service.discoverMovies(MockMovie.genresIdList) } throws RemoteDataSourceException()

        runBlocking {
            dataSource.discoverMovies(MockMovie.genresIdList)
            coVerify { service.discoverMovies(MockMovie.genresIdList) }
        }
    }
}
