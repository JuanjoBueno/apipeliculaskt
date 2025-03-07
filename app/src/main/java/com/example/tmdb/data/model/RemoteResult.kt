package com.example.tmdb.data.model

data class RemoteResult (
    val total: Int,
    val page: Int,
    val results: List<Result>,
)