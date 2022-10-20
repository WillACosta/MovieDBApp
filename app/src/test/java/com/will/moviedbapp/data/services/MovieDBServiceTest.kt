package com.will.moviedbapp.data.services

import com.will.moviedbapp.data.model.PaginatedResponse
import com.will.moviedbapp.resources.mocks.MockMovie
import com.will.moviedbapp.resources.utils.enqueueResponse
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
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

        // TODO: Refactor to generic function to accept any service class
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
        runBlocking {

            mockWebServer.enqueue(MockResponse().setBody("{}"))

            val response = service.getTrendingMovies()
            val request = mockWebServer.takeRequest()

            assertEquals(request.path, "/trending/movie/week")

        }
    }

    @Test
    fun `should fetch trending movies correctly given status code 200`() {
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

}
