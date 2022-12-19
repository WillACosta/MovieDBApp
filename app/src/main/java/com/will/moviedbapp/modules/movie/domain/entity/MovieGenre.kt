package com.will.moviedbapp.modules.movie.domain.entity

import com.google.gson.annotations.SerializedName

data class MovieGenre(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)
