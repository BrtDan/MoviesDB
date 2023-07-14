package com.example.network

data class WrapperTv(
    val searchTv: ResultTvSearch,
    val isFavourite: Boolean
)

fun TvSearchConvert.asWrapper() = this.results.map { WrapperTv(searchTv = it, isFavourite = false) }

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