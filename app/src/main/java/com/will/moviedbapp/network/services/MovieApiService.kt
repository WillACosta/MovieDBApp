package com.will.moviedbapp.network.services

import com.will.moviedbapp.network.model.genre.GenreListResponse
import com.will.moviedbapp.network.model.movie_detail.MovieDetailResponse
import com.will.moviedbapp.network.model.movie_list.MovieListResponse
import com.will.moviedbapp.network.utils.Endpoints
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET(Endpoints.TRENDING)
    suspend fun getTrendingMovies(): MovieListResponse

    @GET(Endpoints.MOVIE_DETAIL)
    suspend fun getMovieById(@Path("movie_id") id: Int): MovieDetailResponse

    @GET(Endpoints.GENRES)
    suspend fun getGenres(): GenreListResponse

    @GET(Endpoints.SEARCH)
    suspend fun searchMovie(@Query("query") query: String): MovieListResponse

    @GET(Endpoints.DISCOVER)
    suspend fun discoverMovies(@Query("with_genres") genresId: Array<Int>?): MovieListResponse
}
