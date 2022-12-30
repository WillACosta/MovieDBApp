package com.will.moviedbapp.domain.entities

import com.google.gson.annotations.SerializedName

data class MovieGenre(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)