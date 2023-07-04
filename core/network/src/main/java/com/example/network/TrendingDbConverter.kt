package com.example.network

data class TrendingConvert(
    val results: List<TrendingDay>
)

data class TrendingDay(
    val id: Int,
    val title: String?,
    val name: String?,
    val original_language: String,
    val overview: String,
    val poster_path: String,
    val release_date: String?,
    val first_air_date: String?,
    val vote_average: Float,
    val vote_count: Int
) {
    val nameTitle: String
        get() = title ?: name ?: ""

    val releaseDate: String
        get() = release_date ?: first_air_date ?: ""
}
