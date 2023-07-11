package com.example.network

data class TrendingWeekConvert(
    val results: List<TrendingActors>
)

data class TrendingActors(
    val id: Int,
    val name: String?,
    val profile_path: String?,
    val known_for: List<knownFor>?
)

data class knownFor(
    val title: String?,
    val original_language: String?,
    val overview: String?,
    val release_date: String?,
    val vote_average: Float?
)