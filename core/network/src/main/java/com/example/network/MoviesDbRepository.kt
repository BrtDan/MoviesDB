package com.example.network

import javax.inject.Inject

class MoviesDbRepository @Inject constructor(
    private val moviesService: MoviesDbApi
) {
    suspend fun getTopRatedMovies(language: String, page: Int): MoviesConvert {
        return moviesService.getTopRatedMovies(language, page)
    }

    suspend fun getTopRatedTv(language: String, page: Int): TvConvert{
        return moviesService.getTopRatedTv(language, page)
    }

    suspend fun getTrendingDay(language: String, page: Int): TrendingConvert{
        return moviesService.getTrendingDay(language, page)
    }

    suspend fun getTrendingWeek(language: String): TrendingWeekConvert{
        return moviesService.getTrendingWeek(language)
    }

    suspend fun searchMovie(language: String, name: String): MoviesConvert{
        return moviesService.searchMovie(language, name)
    }

    suspend fun searchTv(language: String, name: String): TvConvert{
        return moviesService.searchTv(language, name)
    }

}