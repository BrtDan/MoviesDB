package com.example.network

import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

class MoviesDbRepository @Inject constructor(
    private val moviesService: MoviesDbApi,
    private val tvService: MoviesDbApi
) {
    suspend fun getTopRatedMovies(language: String, page: Int): MoviesConvert {
        val moviesConvert = moviesService.getTopRatedMovies(language, page)
        return moviesConvert
    }

    suspend fun getTopRatedTv(language: String, page: Int): TvConvert{
        return tvService.getTopRatedTv(language, page)
    }
}