package com.will.moviedbapp.modules.shared.model

import com.google.gson.annotations.SerializedName
import com.will.moviedbapp.modules.movie.domain.entity.MovieGenre

data class MovieGenresResponse(
    @SerializedName("genres") val genres: List<MovieGenre>
)
