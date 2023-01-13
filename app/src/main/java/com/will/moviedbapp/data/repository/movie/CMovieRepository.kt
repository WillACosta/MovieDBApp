package com.will.moviedbapp.data.repository.movie

import com.will.moviedbapp.data.datasource.movie.MovieDataSource
import com.will.moviedbapp.data.mappers.MovieMapper
import com.will.moviedbapp.domain.entities.Genre
import com.will.moviedbapp.domain.entities.Movie
import com.will.moviedbapp.domain.entities.MovieDetail
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CMovieRepository(
    private val dataSource: MovieDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : MovieRepository {

    override suspend fun getTrendingMovies(): Flow<Result<List<Movie>>> = flow {
        emit(
            dataSource.getTrendingMovies().map {
                MovieMapper.fromMovieListResponseToMovieList(it)
            }
        )
    }.flowOn(dispatcher)

    override suspend fun getMovieDetail(id: Int): Flow<Result<MovieDetail>> = flow {
        emit(
            dataSource.getMovie(id).map {
                MovieMapper.fromMovieDetailResponseToMovieDetail(it)
            }
        )
    }.flowOn(dispatcher)

    override suspend fun search(query: String): Flow<Result<List<Movie>>> = flow {
        emit(
            dataSource.search(query).map {
                MovieMapper.fromMovieListResponseToMovieList(it)
            }
        )
    }.flowOn(dispatcher)

    override suspend fun getGenres(): Flow<Result<List<Genre>>> = flow {
        emit(
            dataSource.getGenres().map {
                MovieMapper.fromGenreListResponseToGenreList(it)
            }
        )
    }.flowOn(dispatcher)

    override suspend fun discoverMovies(genresId: Array<Int>?): Flow<Result<List<Movie>>> = flow {
        emit(
            dataSource.discoverMovies(genresId).map {
                MovieMapper.fromMovieListResponseToMovieList(it)
            }
        )
    }.flowOn(dispatcher)

}
