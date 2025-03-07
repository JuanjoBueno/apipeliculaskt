package com.example.tmdb.data.model

import com.google.gson.annotations.SerializedName

data class Result (
    val id: Int,
    val title: String,
    @SerializedName("poster_path")
    val posterPath: String,
)