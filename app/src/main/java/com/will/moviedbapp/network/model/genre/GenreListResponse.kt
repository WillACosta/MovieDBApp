package com.will.moviedbapp.network.model.genre

import com.google.gson.annotations.SerializedName

data class GenreListResponse(
    @SerializedName("genres") val genres: List<GenreResponse>
)
