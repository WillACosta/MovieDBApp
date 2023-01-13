package com.will.moviedbapp.data.mappers

import com.will.moviedbapp.domain.entities.Genre
import com.will.moviedbapp.domain.entities.Movie
import com.will.moviedbapp.domain.entities.MovieDetail
import com.will.moviedbapp.network.model.genre.GenreListResponse
import com.will.moviedbapp.network.model.genre.GenreResponse
import com.will.moviedbapp.network.model.movie_detail.MovieDetailResponse
import com.will.moviedbapp.network.model.movie_list.MovieListResponse
import com.will.moviedbapp.network.model.movie_list.MovieResponse

object MovieMapper {
    private fun fromMovieResponseToMovie(movieResponse: MovieResponse): Movie {
        return Movie(
            id = movieResponse.id,
            genreIds = movieResponse.genreIds,
            overview = movieResponse.overview,
            posterPath = movieResponse.posterPath,
            releaseDate = movieResponse.releaseDate,
            runtime = movieResponse.runtime,
            title = movieResponse.title,
            voteAverage = movieResponse.voteAverage,
        )
    }

    fun fromMovieListResponseToMovieList(movieListResponse: MovieListResponse): List<Movie> {
        return movieListResponse.results.map { fromMovieResponseToMovie(it) }
    }

    fun fromMovieDetailResponseToMovieDetail(movieDetailResponse: MovieDetailResponse): MovieDetail {
        return MovieDetail(
            genres = fromMovieDetailGenresToGenreList(movieDetailResponse.genres),
            homePage = movieDetailResponse.homePage,
            overview = movieDetailResponse.overview,
            popularity = movieDetailResponse.popularity,
            posterPath = movieDetailResponse.posterPath,
            releaseDate = movieDetailResponse.releaseDate,
            runtime = movieDetailResponse.runtime,
            title = movieDetailResponse.title,
            hasVideo = movieDetailResponse.video,
            voteAverage = movieDetailResponse.voteAverage,
            voteCount = movieDetailResponse.voteCount,
            subTitle = movieDetailResponse.tagLine,
        )
    }

    private fun fromMovieDetailGenresToGenreList(genres: List<GenreResponse>): List<Genre> {
        return genres.map { fromGenreResponseToGenre(it) }
    }

    private fun fromGenreResponseToGenre(genreResponse: GenreResponse): Genre {
        return Genre(
            id = genreResponse.id,
            description = genreResponse.name
        )
    }

    fun fromGenreListResponseToGenreList(genreResponseList: GenreListResponse): List<Genre> {
        return genreResponseList.genres.map {
            fromGenreResponseToGenre(it)
        }
    }
}
