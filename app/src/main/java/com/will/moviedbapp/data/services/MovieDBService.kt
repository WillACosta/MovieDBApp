package com.will.moviedbapp.data.services

import com.will.moviedbapp.data.model.Movie
import com.will.moviedbapp.data.model.PaginatedResponse
import retrofit2.http.GET

interface MovieDBService {

    @GET("trending/movie/week")
    suspend fun getTrendingMovies(): PaginatedResponse<List<Movie>>
}
