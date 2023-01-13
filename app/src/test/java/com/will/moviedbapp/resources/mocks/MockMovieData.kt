package com.will.moviedbapp.resources.mocks

import com.will.moviedbapp.domain.entities.Genre
import com.will.moviedbapp.domain.entities.Movie
import com.will.moviedbapp.domain.entities.MovieDetail

object MockMovieData {
    private val movie = Movie(
        genreIds = listOf(878, 12),
        id = 438631,
        overview = "Paul Atreides, a brilliant and gifted young man born into a great destiny...",
        posterPath = "/d5NXSklXo0qyIYkgV94XAgMIckC.jpg",
        releaseDate = "2021-09-08",
        title = "Dune",
        voteAverage = 7.856,
        runtime = 155
    )

    val genre = Genre(
        id = 1,
        description = "action"
    )

    val genreList = listOf(genre)

    val movieList = listOf(movie)

    val movieDetail = MovieDetail(
        genres = genreList,
        homePage = "",
        hasVideo = false,
        subTitle = "it begins",
        overview = "Paul Atreides, a brilliant and gifted young man born into a great destiny...",
        posterPath = "/d5NXSklXo0qyIYkgV94XAgMIckC.jpg",
        releaseDate = "2021-09-08",
        title = "Dune",
        voteAverage = 7.856,
        voteCount = 7826,
        runtime = 155,
        popularity = 146.856,
    )

}
