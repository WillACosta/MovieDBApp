package com.will.moviedbapp.data.datasource.movie

import com.will.moviedbapp.data.utils.safeApiCall
import com.will.moviedbapp.network.model.genre.GenreListResponse
import com.will.moviedbapp.network.model.movie_detail.MovieDetailResponse
import com.will.moviedbapp.network.model.movie_list.MovieListResponse
import com.will.moviedbapp.network.services.MovieApiService

class CMovieDataSource(private val service: MovieApiService) : MovieDataSource {

    override suspend fun getTrendingMovies(): Result<MovieListResponse> = safeApiCall {
        service.getTrendingMovies()
    }

    override suspend fun getGenres(): Result<GenreListResponse> = safeApiCall {
        service.getGenres()
    }

    override suspend fun getMovie(id: Int): Result<MovieDetailResponse> = safeApiCall {
        service.getMovieById(id)
    }

    override suspend fun search(query: String): Result<MovieListResponse> = safeApiCall {
        service.searchMovie(query)
    }

    override suspend fun discoverMovies(genresId: Array<Int>?): Result<MovieListResponse> {
        return safeApiCall {
            service.discoverMovies(genresId)
        }
    }

}

