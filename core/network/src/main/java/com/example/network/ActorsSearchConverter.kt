package com.example.network

data class ActorsSearchConvert(
    val results: List<ActorsSearch>
)

data class ActorsSearch(
    val id: Int,
    val name: String?,
    val profile_path: String?,
    val known_for: List<actorsKnownFor>?
)

data class actorsKnownFor(
    val title: String?,
    val original_language: String?,
    val overview: String?,
    val release_date: String?,
    val vote_average: Float?
)
