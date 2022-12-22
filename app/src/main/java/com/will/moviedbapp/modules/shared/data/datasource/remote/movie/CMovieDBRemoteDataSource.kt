package com.will.moviedbapp.modules.shared.data.datasource.remote.movie

import com.will.moviedbapp.core.errors.RemoteDataSourceException
import com.will.moviedbapp.modules.movie.domain.entity.Movie
import com.will.moviedbapp.modules.movie.domain.entity.MovieGenre
import com.will.moviedbapp.modules.shared.data.services.MovieDBService

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
            service.getGenres().genres
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
