package com.example.network

import javax.inject.Inject

class MoviesDbRepository @Inject constructor(
    private val moviesService: MoviesDbApi
) {
    suspend fun getTopRatedMovies(language: String, page: Int): MoviesConvert {
        val moviesConvert = moviesService.getTopRatedMovies(language, page)
        return moviesConvert
    }
}