package com.will.moviedbapp.modules.shared.data.repository.remote.movie

import com.will.moviedbapp.core.state.Result
import com.will.moviedbapp.modules.movie.domain.entity.Movie
import com.will.moviedbapp.modules.movie.domain.entity.MovieGenre
import com.will.moviedbapp.modules.shared.data.datasource.remote.movie.MovieDBRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class CMovieRepository(private val remote: MovieDBRemoteDataSource) : MovieRepository {
    override suspend fun getTrendingMovies(): Flow<Result<List<Movie>>> {
        return flow {
            emit(Result.Loading)
            emit(Result.successOrEmpty(remote.getTrendingMovies()))
        }.catch {
            emit(Result.Error(it))
        }
    }

    override suspend fun getMovie(id: Int): Flow<Result<Movie>> {
        return flow {
            emit(Result.Loading)
            emit(Result.Success(remote.getMovie(id)))
        }.catch {
            emit(Result.Error(it))
        }
    }

    override suspend fun search(query: String): Flow<Result<List<Movie>>> {
        return flow {
            emit(Result.Loading)
            emit(Result.successOrEmpty(remote.search(query)))
        }.catch {
            emit(Result.Error(it))
        }
    }

    override suspend fun getGenres(): Flow<Result<List<MovieGenre>>> {
        return flow {
            emit(Result.loading())
            emit(Result.successOrEmpty(remote.getGenres()))
        }.catch {
            emit(Result.error(it))
        }
    }
}
