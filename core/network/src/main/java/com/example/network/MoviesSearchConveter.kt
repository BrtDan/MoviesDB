package com.example.network

data class MoviesSearchConvert(
    val results: List<ResultSearch>
)

data class ResultSearch(
    val id: Int?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val vote_average: Float?,
    val vote_count: Int?
)