package com.example.network

data class WrapperActors(
    val searchActors: ActorsSearch,
    val isFavourite: Boolean
)

fun ActorsSearchConvert.asWrapper() = this.results.map { WrapperActors(searchActors = it, isFavourite = false) }


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
    val name: String?,
    val original_language: String?,
    val overview: String?,
    val release_date: String?,
    val first_air_date: String?,
    val vote_average: Float?
){
    val nameTitle: String
    get() = title ?: name ?: ""

    val releaseDate: String
    get() = release_date ?: first_air_date ?: ""
}