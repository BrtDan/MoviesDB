package com.example.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesDbRepository @Inject constructor(
    private val moviesService: MoviesDbApi,
    private val moviesSearchService: MoviesDbApi,
    private val databaseMovies: MoviesDatabase
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

    suspend fun checkIfIsFavourite(id: Int) : Int{
        return databaseMovies.moviesDao().checkIfIsFavourite(id)
    }

    suspend fun insertIntoDB(id: Int, name: String, release_date: String, posterPath: String, original_lang: String, overview: String, vote_avg: Float, typeMedia: String) {
        withContext(Dispatchers.IO) {
            databaseMovies.moviesDao().insert(toEntity(id, name, release_date, posterPath, original_lang, overview, vote_avg, typeMedia))
        }
    }

    suspend fun deleteMoviesFromDB(id: Int) {
        withContext(Dispatchers.IO) {
            databaseMovies.moviesDao().delete(id)
        }
    }

    suspend fun getDataFromDB() : List<MoviesDB>?{
        return databaseMovies.moviesDao().getDataFromDB()
    }

}