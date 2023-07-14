package com.example.network

data class WrapperMovie(
    val search: ResultSearch,
    val isFavourite: Boolean
)

fun MoviesSearchConvert.asWrapper() = this.results.map { WrapperMovie(search = it, isFavourite = false) }

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