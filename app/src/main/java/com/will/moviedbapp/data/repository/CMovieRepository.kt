package com.will.moviedbapp.data.repository

import com.will.moviedbapp.core.state.StateResult
import com.will.moviedbapp.data.datasource.movieDb.MovieDBRemoteDataSource
import com.will.moviedbapp.data.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class CMovieRepository(private val remote: MovieDBRemoteDataSource) : MovieRepository {
    override suspend fun getTrendingMovies(): Flow<StateResult<List<Movie>>> {
        return flow {
            emit(StateResult.Loading)

            val movies = remote.getTrendingMovies()

            emit(StateResult.successOrEmpty(movies))
        }.catch { ex ->
            emit(StateResult.Error(ex))
        }
    }

    override suspend fun getMovie(id: Int): Flow<StateResult<Movie>> {
        return flow {
            emit(StateResult.Loading)

            val movie = remote.getMovie(id)
            emit(StateResult.Success(movie))
        }.catch { ex ->
            emit(StateResult.Error(ex))
        }
    }

    override suspend fun search(query: String): Flow<StateResult<List<Movie>>> {
        return flow {
            emit(StateResult.Loading)

            val movies = remote.search(query)

            emit(StateResult.successOrEmpty(movies))
        }.catch { ex ->
            emit(StateResult.Error(ex))
        }
    }
}
