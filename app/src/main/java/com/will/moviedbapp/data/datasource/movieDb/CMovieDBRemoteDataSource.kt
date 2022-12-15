package com.will.moviedbapp.data.datasource.movieDb

import com.will.moviedbapp.core.errors.RemoteDataSourceException
import com.will.moviedbapp.data.services.MovieDBService
import com.will.moviedbapp.domain.model.Movie
import com.will.moviedbapp.domain.model.MovieGenre

class CMovieDBRemoteDataSource(private val service: MovieDBService) : MovieDBRemoteDataSource {
    override suspend fun getTrendingMovies(): List<Movie> {
        return try {
            service.getTrendingMovies().results
        } catch (ex: Exception) {
            throw RemoteDataSourceException()
        }
    }

    override suspend fun getGenres(): List<MovieGenre> {
        return try {
            service.getGenres()
        } catch (ex: Exception) {
            throw RemoteDataSourceException()
        }
    }

    override suspend fun getMovie(id: Int): Movie {
        return try {
            service.getMovieById(id)
        } catch (ex: Exception) {
            throw RemoteDataSourceException()
        }
    }

    override suspend fun search(query: String): List<Movie> {
        return try {
            service.searchMovie(query).results
        } catch (ex: Exception) {
            throw RemoteDataSourceException()
        }
    }
}
