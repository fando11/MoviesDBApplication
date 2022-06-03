package com.example.common.entity.movie.movie_trailer


import com.google.gson.annotations.SerializedName

data class MovieTrailerResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<Result>
)