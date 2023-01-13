package com.will.moviedbapp.domain.entities

data class Movie(
    val id: Int,
    val genreIds: List<Int>,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val runtime: Int?,
    val title: String,
    val voteAverage: Double,
)
