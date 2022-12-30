package com.will.moviedbapp.data.datasource.movie

import com.will.moviedbapp.core.errors.RemoteDataSourceException
import com.will.moviedbapp.domain.entities.Movie
import com.will.moviedbapp.domain.entities.MovieGenre
import com.will.moviedbapp.network.services.MovieDBService

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

    override suspend fun discoverMovies(genresId: Array<Int>?): List<Movie> {
        return try {
            service.discoverMovies(genresId).results
        } catch (ex: Exception) {
            throw RemoteDataSourceException()
        }
    }
}

