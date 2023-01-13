package com.will.moviedbapp.network.services

import com.will.moviedbapp.network.model.movie_list.MovieListResponse
import com.will.moviedbapp.resources.mocks.MockApiData
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
class MovieApiServiceTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var service: MovieApiService

    @Before
    fun createService() {
        val converterFactory = GsonConverterFactory.create()

        mockWebServer = MockWebServer()

        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(converterFactory)
            .build().create(MovieApiService::class.java)
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
        mockWebServer.enqueueResponse(MockApiData.trendingMovieJson, 200)

        runBlocking {
            val response = service.getTrendingMovies()
            val expected = MovieListResponse(
                1,
                listOf(MockApiData.movieResponse),
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
    fun `should fetch movie detail correctly with status code 200`() {
        mockWebServer.enqueueResponse(MockApiData.movieDetailJSON, 200)

        runBlocking {
            val response = service.getMovieById(1)
            assertEquals(MockApiData.movieDetailResponse, response)
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
        mockWebServer.enqueueResponse(MockApiData.trendingMovieJson, 200)

        runBlocking {
            val response = service.searchMovie("dune")
            val expected = MovieListResponse(
                1,
                listOf(MockApiData.movieResponse),
                2,
                20
            )

            assertEquals(expected, response)
        }
    }
}
