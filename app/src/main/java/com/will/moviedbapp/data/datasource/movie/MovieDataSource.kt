package com.will.moviedbapp.data.datasource.movie

import com.will.moviedbapp.network.model.genre.GenreListResponse
import com.will.moviedbapp.network.model.movie_detail.MovieDetailResponse
import com.will.moviedbapp.network.model.movie_list.MovieListResponse

interface MovieDataSource {
    suspend fun getTrendingMovies(): Result<MovieListResponse>
    suspend fun getGenres(): Result<GenreListResponse>
    suspend fun getMovie(id: Int): Result<MovieDetailResponse>
    suspend fun search(query: String): Result<MovieListResponse>
    suspend fun discoverMovies(genresId: Array<Int>?): Result<MovieListResponse>
}
