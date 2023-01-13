package com.will.moviedbapp.network.model.movie_detail

import com.google.gson.annotations.SerializedName
import com.will.moviedbapp.network.model.genre.GenreResponse

data class MovieDetailResponse(
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("genres") val genres: List<GenreResponse>,
    @SerializedName("homepage") val homePage: String,
    @SerializedName("imdb_id") val imdbId: String,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("runtime") val runtime: Int?,
    @SerializedName("title") val title: String,
    @SerializedName("video") val video: Boolean,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("revenue") val revenue: Int,
    @SerializedName("status") val status: String,
    @SerializedName("tagline") val tagLine: String,
)
