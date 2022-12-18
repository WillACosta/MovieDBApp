package com.will.moviedbapp.modules.shared.data.services

import com.will.moviedbapp.modules.home.domain.model.Movie
import com.will.moviedbapp.modules.home.domain.model.MovieGenre
import com.will.moviedbapp.modules.shared.model.PaginatedResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDBService {

    @GET("trending/movie/week")
    suspend fun getTrendingMovies(): PaginatedResponse<List<Movie>>

    @GET("movie/{movie_id}")
    suspend fun getMovieById(@Path("movie_id") id: Int): Movie

    @GET("genre/movie/list")
    suspend fun getGenres(): List<MovieGenre>

    @GET("search/movie")
    suspend fun searchMovie(@Query("query") query: String): PaginatedResponse<List<Movie>>
}
