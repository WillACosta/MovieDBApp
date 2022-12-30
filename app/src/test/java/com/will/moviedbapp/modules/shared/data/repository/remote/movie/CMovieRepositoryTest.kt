package com.will.moviedbapp.modules.shared.data.repository.remote.movie

import com.will.moviedbapp.core.errors.RemoteDataSourceException
import com.will.moviedbapp.core.state.Result
import com.will.moviedbapp.data.repository.remote.movie.CMovieRepository
import com.will.moviedbapp.data.repository.remote.movie.MovieRepository
import com.will.moviedbapp.data.datasource.movie.MovieDBRemoteDataSource
import com.will.moviedbapp.resources.mocks.MockMovie
import com.will.moviedbapp.resources.mocks.MockResult
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

    private val expectedErrorStateList = MockResult.expectedErrorStateList
    private val expectedEmptyStateList = MockResult.expectedEmptyStateList

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = CMovieRepository(remote)
    }

    @Test
    fun `should call getTrendingMovies and returns a list state of Result with Success`() {
        val listMovie = MockMovie.listMovie
        val expectedStates = MockResult.expectedSuccessListMovie

        coEvery { remote.getTrendingMovies() } returns listMovie

        runBlocking {
            val flow = repository.getTrendingMovies()
            val results = flow.toList()

            assertEquals(2, results.count())
            assertEquals(expectedStates, results)
        }
    }

    @Test
    fun `should returns state as Empty if remote returns an empty list for getTrendingMovies`() {
        coEvery { remote.getTrendingMovies() } returns emptyList()

        runBlocking {
            val flow = repository.getTrendingMovies()
            val results = flow.toList()

            assertEquals(2, results.count())
            assertEquals(expectedEmptyStateList, results)
        }
    }

    @Test
    fun `should returns state as a Error if remote calls was unsuccessful for getTrendingMovies`() {
        coEvery { remote.getTrendingMovies() } throws RemoteDataSourceException()

        runBlocking {
            val flow = repository.getTrendingMovies()
            val results = flow.toList()

            assertEquals(2, results.count())
            assertEquals(expectedErrorStateList[0], results[0])
            assert(expectedErrorStateList[1] is Result.Error)
        }
    }

    @Test
    fun `should returns a list state of Result with Success`() {
        val movie = MockMovie.movie
        val expectedStates = MockResult.expectedSuccessMovie

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
    fun `should returns state as a Error if remote calls was unsuccessfully`() {
        coEvery { remote.getMovie(any()) } throws RemoteDataSourceException()

        runBlocking {
            val flow = repository.getMovie(MockMovie.movieID)
            val results = flow.toList()

            assertEquals(2, results.count())
            assertEquals(expectedErrorStateList[0], results[0])
            assert(expectedErrorStateList[1] is Result.Error)
        }
    }

    @Test
    fun `should call search and returns a list state of Result with Success`() {
        coEvery { remote.search(any()) } returns MockMovie.listMovie

        val expectedStates = MockResult.expectedSuccessListMovie

        runBlocking {
            val flow = repository.search(MockMovie.searchQuery)
            val results = flow.toList()

            assertEquals(2, results.count())
            assertEquals(expectedStates, results)
        }
    }

    @Test
    fun `should returns last Result as a Empty if remote returns an empty list`() {
        coEvery { remote.search(any()) } returns emptyList()

        runBlocking {
            val flow = repository.search(MockMovie.searchQuery)
            val results = flow.toList()

            assertEquals(2, results.count())
            assertEquals(expectedEmptyStateList, results)
        }
    }

    @Test
    fun `should returns last Result as a Error if repository calls was unsuccessfully`() {
        coEvery { remote.search(any()) } throws RemoteDataSourceException()

        runBlocking {
            val flow = repository.search(MockMovie.searchQuery)
            val results = flow.toList()

            assertEquals(2, results.count())
            assertEquals(expectedErrorStateList[0], results[0])
            assert(expectedErrorStateList[1] is Result.Error)
        }
    }

    @Test
    fun `should call getGenres and returns an Result with list of MovieGenre`() {
        coEvery { remote.getGenres() } returns MockMovie.genresList

        runBlocking {
            val flow = repository.getGenres()
            val results = flow.toList()

            assertEquals(2, results.count())
            assertEquals(Result.successOrEmpty(MockMovie.genresList), results[1])
        }
    }

    @Test
    fun `should call discoverMovies and returns an Result with list of Movie`() {
        coEvery { remote.discoverMovies(MockMovie.genresIdList) } returns MockMovie.listMovie

        runBlocking {
            val flow = repository.discoverMovies(MockMovie.genresIdList)
            val results = flow.toList()

            assertEquals(2, results.count())
            assertEquals(Result.successOrEmpty(MockMovie.genresList), results[1])
        }
    }
}
