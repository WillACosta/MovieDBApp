package com.will.moviedbapp.domain.entities

data class MovieDetail(
    val genres: List<Genre>,
    val homePage: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val runtime: Int?,
    val title: String,
    val hasVideo: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    val subTitle: String,
)
