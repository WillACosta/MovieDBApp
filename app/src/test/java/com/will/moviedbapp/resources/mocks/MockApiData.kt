package com.will.moviedbapp.resources.mocks

import com.will.moviedbapp.network.model.genre.GenreListResponse
import com.will.moviedbapp.network.model.genre.GenreResponse
import com.will.moviedbapp.network.model.movie_detail.MovieDetailResponse
import com.will.moviedbapp.network.model.movie_list.MovieListResponse
import com.will.moviedbapp.network.model.movie_list.MovieResponse

object MockApiData {
    const val movieID = 438631
    val searchQuery = "dune"

    val genreResponse = GenreResponse(
        id = 1,
        name = "action"
    )

    val genreListResponse = GenreListResponse(
        genres = listOf(genreResponse)
    )

    val movieResponse = MovieResponse(
        adult = false,
        backdropPath = "/jYEW5xZkZk2WTrdbMGAPFuBqbDc.jpg",
        genreIds = listOf(878, 12),
        id = 438631,
        originalLanguage = "en",
        originalTitle = "Dune",
        overview = "Paul Atreides, a brilliant and gifted young man born into a great destiny...",
        popularity = 146.856,
        posterPath = "/d5NXSklXo0qyIYkgV94XAgMIckC.jpg",
        releaseDate = "2021-09-08",
        title = "Dune",
        video = false,
        voteAverage = 7.856,
        voteCount = 7826,
        runtime = 155
    )

    val movieListResponse = MovieListResponse(
        page = 1,
        results = listOf(movieResponse),
        totalPages = 1,
        totalResults = 1
    )

    val movieDetailResponse = MovieDetailResponse(
        adult = false,
        homePage = "",
        imdbId = "tt1160419",
        status = "Released",
        revenue = 1,
        tagLine = "it begins",
        backdropPath = "/jYEW5xZkZk2WTrdbMGAPFuBqbDc.jpg",
        genres = listOf(genreResponse),
        originalLanguage = "en",
        originalTitle = "Dune",
        overview = "Paul Atreides, a brilliant and gifted young man born into a great destiny...",
        popularity = 146.856,
        posterPath = "/d5NXSklXo0qyIYkgV94XAgMIckC.jpg",
        releaseDate = "2021-09-08",
        title = "Dune",
        video = false,
        voteAverage = 7.856,
        voteCount = 7826,
        runtime = 155
    )

    const val movieDetailJSON = """
       {
            "adult": false,
            "homepage": "",
            "imdb_id": "tt1160419",
            "status": "Released",
            "revenue": 1,
            "tagline": "it begins",
            "backdrop_path": "/jYEW5xZkZk2WTrdbMGAPFuBqbDc.jpg",
            "genres": [{
                "id": 1,
                "name": "action"
            }],
            "original_language": "en",
            "original_title": "Dune",
            "overview": "Paul Atreides, a brilliant and gifted young man born into a great destiny...",
            "popularity": 146.856,
            "poster_path": "/d5NXSklXo0qyIYkgV94XAgMIckC.jpg",
            "release_date": "2021-09-08",
            "title": "Dune",
            "video": false,
            "vote_average": 7.856,
            "vote_count": 7826,
            "runtime": 155
       }
    """

    const val movieJson = """
        {
          "poster_path": "/d5NXSklXo0qyIYkgV94XAgMIckC.jpg",
          "adult": false,
          "overview": "Paul Atreides, a brilliant and gifted young man born into a great destiny...",
          "release_date": "2021-09-08",
          "genre_ids": [
            878,
            12
          ],
          "id": 438631,
          "original_title": "Dune",
          "original_language": "en",
          "title": "Dune",
          "backdrop_path": "/jYEW5xZkZk2WTrdbMGAPFuBqbDc.jpg",
          "popularity": 146.856,
          "vote_count": 7826,
          "video": false,
          "vote_average": 7.856,
          "runtime": 155
        }  
       """

    const val trendingMovieJson = """
        {
          "page": 1,
          "results": [
            {
              "poster_path": "/d5NXSklXo0qyIYkgV94XAgMIckC.jpg",
              "adult": false,
              "overview": "Paul Atreides, a brilliant and gifted young man born into a great destiny...",
              "release_date": "2021-09-08",
              "genre_ids": [
                878,
                12
              ],
              "id": 438631,
              "original_title": "Dune",
              "original_language": "en",
              "title": "Dune",
              "backdrop_path": "/jYEW5xZkZk2WTrdbMGAPFuBqbDc.jpg",
              "popularity": 146.856,
              "vote_count": 7826,
              "video": false,
              "vote_average": 7.856,
              "runtime": 155
            }
          ],
          "total_results": 20,
          "total_pages": 2
        }
        """
}
