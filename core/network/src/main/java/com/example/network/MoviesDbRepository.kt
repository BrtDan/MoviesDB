package com.example.network

import javax.inject.Inject

class MoviesDbRepository @Inject constructor(
    private val moviesService: MoviesDbApi,
    private val moviesSearchService: MoviesDbApi
) {
    suspend fun getTopRatedMovies(language: String, page: Int): MoviesConvert {
        return moviesService.getTopRatedMovies(language, page)
    }

    suspend fun getDetailsMovies(id: Int, language: String): MoviesDetailsConvert {
        return moviesService.getDetailsMovies(id, language)
    }

    suspend fun getDetailsTv(id: Int, language: String): TvDetailsConvert {
        return moviesService.getDetailsTv(id, language)
    }

    suspend fun getTopRatedTv(language: String, page: Int): TvConvert{
        return moviesService.getTopRatedTv(language, page)
    }

    suspend fun getTrendingDay(language: String): TrendingConvert{
        return moviesService.getTrendingDay(language)
    }

    suspend fun getTrendingWeek(language: String): TrendingWeekConvert{
        return moviesService.getTrendingWeek(language)
    }

    suspend fun getDetailsTrendingWeek(id: Int, language: String): TrendingWeekDetailsConvert{
        return moviesService.getDetailsTrendingWeek(id, language)
    }

    suspend fun searchMovie(language: String, name: String): MoviesSearchConvert {
        return moviesSearchService.searchMovie(language, name)
    }

    suspend fun searchTv(language: String, name: String): TvSearchConvert{
        return moviesService.searchTv(language, name)
    }

    suspend fun searchActors(language: String, name: String): ActorsSearchConvert{
        return moviesService.searchActors(language, name)
    }

}