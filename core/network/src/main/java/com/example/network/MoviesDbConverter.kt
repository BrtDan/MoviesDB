package com.example.network

data class MoviesConvert(
    val results: List<Result>
)

data class Result(
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Float,
    val vote_count: Int
)
