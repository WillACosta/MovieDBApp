package com.will.moviedbapp.data.services

import com.will.moviedbapp.data.model.Movie
import com.will.moviedbapp.data.model.PaginatedResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDBService {

    @GET("trending/movie/week")
    suspend fun getTrendingMovies(): PaginatedResponse<List<Movie>>

    @GET("movie/{movie_id}")
    suspend fun getMovieById(@Path("movie_id") id: Int): Movie
}
