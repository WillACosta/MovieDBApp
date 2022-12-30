package com.will.moviedbapp.network.model

import com.google.gson.annotations.SerializedName
import com.will.moviedbapp.domain.entities.MovieGenre

data class MovieGenresResponse(
    @SerializedName("genres") val genres: List<MovieGenre>
)
