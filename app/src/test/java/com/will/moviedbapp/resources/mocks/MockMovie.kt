package com.will.moviedbapp.resources.mocks

import com.will.moviedbapp.data.model.Movie
import com.will.moviedbapp.data.model.PaginatedResponse

object MockMovie {
    const val movieID = 438631
    val searchQuery = "dune"

    val movie = Movie(
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
        voteCount = 7826
    )

    val listMovie = listOf(movie)

    val paginatedResponse = PaginatedResponse(
        page = 1,
        results = listMovie,
        totalPages = 2,
        totalResults = 20
    )

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
          "vote_average": 7.856
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
              "vote_average": 7.856
            }
          ],
          "total_results": 20,
          "total_pages": 2
        }
        """
}
