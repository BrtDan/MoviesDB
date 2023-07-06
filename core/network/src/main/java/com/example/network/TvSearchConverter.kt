package com.example.network

data class TvSearchConvert(
    val results: List<ResultTvSearch>
)

data class ResultTvSearch(
    val id: Int?,
    val original_language: String?,
    val original_name: String?,
    val overview: String?,
    val poster_path: String?,
    val first_air_date: String?,
    val name: String?,
    val vote_average: Float?,
    val vote_count: Int?
)