package com.example.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesDbApi {
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query(value = "language") language: String,
        @Query(value = "page") page: Int
    ) : MoviesConvert

    @GET("movie/{id}")
    suspend fun getDetailsMovies(
        @Path("id") id: Int,
        @Query("language") language: String
    ): MoviesDetailsConvert

    @GET("tv/{id}")
    suspend fun getDetailsTv(
        @Path("id") id: Int,
        @Query("language") language: String
    ): TvDetailsConvert

    @GET("person/{id}")
    suspend fun getDetailsTrendingWeek(
        @Path("id") id: Int,
        @Query("language") language: String
    ): TrendingWeekDetailsConvert

    @GET("tv/top_rated")
    suspend fun getTopRatedTv(
        @Query(value = "language") language: String,
        @Query(value = "page") page: Int
    ): TvConvert

    @GET("trending/all/day?")
    suspend fun getTrendingDay(
        @Query(value = "language") language: String
    ): TrendingConvert

    @GET("trending/person/week?")
    suspend fun getTrendingWeek(
        @Query(value = "language") language: String
    ): TrendingWeekConvert

    @GET("search/movie?")
    suspend fun searchMovie(
        @Query(value = "query") query: String,
        @Query(value = "language") language: String
    ): MoviesSearchConvert

    @GET("search/tv?")
    suspend fun searchTv(
        @Query(value = "query") query: String,
        @Query(value = "language") language: String
    ): TvSearchConvert

    @GET("search/person?")
    suspend fun searchActors(
        @Query(value = "query") query: String,
        @Query(value = "language") language: String
    ): ActorsSearchConvert

}