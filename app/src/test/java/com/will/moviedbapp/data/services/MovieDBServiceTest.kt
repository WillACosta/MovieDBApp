package com.will.moviedbapp.data.services

import com.will.moviedbapp.modules.shared.model.PaginatedResponse
import com.will.moviedbapp.modules.shared.data.services.MovieDBService
import com.will.moviedbapp.resources.mocks.MockMovie
import com.will.moviedbapp.resources.utils.enqueueResponse
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class MovieDBServiceTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var service: MovieDBService

    @Before
    fun createService() {
        val converterFactory = GsonConverterFactory.create()

        mockWebServer = MockWebServer()

        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(converterFactory)
            .build().create(MovieDBService::class.java)
    }

    @After
    fun stopServer() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should call getTrendingMovies and match correct endpoint`() {
        mockWebServer.enqueue(MockResponse().setBody("{}"))

        runBlocking {
            service.getTrendingMovies()
            val request = mockWebServer.takeRequest()

            assertEquals(request.path, "/trending/movie/week")
        }
    }

    @Test
    fun `should fetch trending movies correctly with status code 200`() {
        mockWebServer.enqueueResponse(MockMovie.trendingMovieJson, 200)

        runBlocking {
            val response = service.getTrendingMovies()
            val expected = PaginatedResponse(
                1,
                listOf(MockMovie.movie),
                2,
                20
            )

            assertEquals(expected, response)
        }
    }

    @Test
    fun `should call getMovieById and match correct endpoint with given id`() {
        mockWebServer.enqueue(MockResponse().setBody("{}"))

        runBlocking {
            service.getMovieById(555)
            val request = mockWebServer.takeRequest()

            assertEquals(request.path, "/movie/555")
        }
    }

    @Test
    fun `should fetch movie correctly with status code 200`() {
        mockWebServer.enqueueResponse(MockMovie.movieJson, 200)

        runBlocking {
            val response = service.getMovieById(555)
            val expected = MockMovie.movie

            assertEquals(expected, response)
        }
    }

    @Test
    fun `should call searchMovie and match correct endpoint with given search query`() {
        mockWebServer.enqueue(MockResponse().setBody("{}"))

        runBlocking {
            service.searchMovie("dune")
            val request = mockWebServer.takeRequest()

            assertEquals(request.path, "/search/movie?query=dune")
        }
    }

    @Test
    fun `should fetch a List of Movie correctly with status code 200`() {
        mockWebServer.enqueueResponse(MockMovie.trendingMovieJson, 200)

        runBlocking {
            val response = service.searchMovie("dune")
            val expected = PaginatedResponse(
                1,
                listOf(MockMovie.movie),
                2,
                20
            )

            assertEquals(expected, response)
        }
    }
}
