package com.example.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesDbApi {
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query(value = "language") language: String,
        @Query(value = "page") page: Int
    ) : MoviesConvert

    @GET("tv/top_rated")
    suspend fun getTopRatedTv(
        @Query(value = "language") language: String,
        @Query(value = "page") page: Int
    ): TvConvert
}