package com.example.moviesdb

import android.text.style.IconMarginSpan
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.ActorsSearchConvert
import com.example.network.MoviesConvert
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.network.MoviesDbRepository
import com.example.network.MoviesSearchConvert
import com.example.network.TrendingConvert
import com.example.network.TrendingWeekConvert
import com.example.network.TvConvert
import com.example.network.TvSearchConvert
import kotlinx.coroutines.launch
import javax.inject.Inject

data class Response(
    val isLoading: Boolean,
    val movieData: MoviesConvert?,
    val tvData: TvConvert?,
    val trendingDay: TrendingConvert?,
    val trendingWeek: TrendingWeekConvert?,
    val movieSearch: MoviesSearchConvert?,
    val actorsSearch: ActorsSearchConvert?,
    val tvSearch:TvSearchConvert?
)

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val moviesDbRepository : MoviesDbRepository
): ViewModel() {

    private val _text = MutableLiveData<Response>()
    val text: LiveData<Response> = _text

    fun getTopRatedMovies(language: String, page: Int) {
        viewModelScope.launch {
            _text.value = Response(isLoading = true, movieData = null, trendingDay = null, tvData = null, trendingWeek = null, movieSearch = null, tvSearch = null, actorsSearch = null)
            val movie = moviesDbRepository.getTopRatedMovies(language, page)
            _text.value = Response(isLoading = false, movieData = movie, trendingDay = null, tvData = null, trendingWeek = null, movieSearch = null, tvSearch = null, actorsSearch = null)
        }
    }

    suspend fun getTopRatedTv(language: String, page: Int) {
        viewModelScope.launch {
            _text.value = Response(isLoading = true, movieData = null, tvData = null, trendingDay = null, trendingWeek = null, movieSearch = null, tvSearch = null, actorsSearch = null)
            val tv = moviesDbRepository.getTopRatedTv(language, page)
            _text.value = Response(isLoading = false, movieData = null, tvData = tv, trendingDay = null, trendingWeek = null, movieSearch = null, tvSearch = null, actorsSearch = null)
        }
    }

    fun getTrendingDay(language: String) {
        viewModelScope.launch {
            _text.value = Response(isLoading = true, movieData = null, tvData = null, trendingDay = null, trendingWeek = null, movieSearch = null, tvSearch = null, actorsSearch = null)
            val trending = moviesDbRepository.getTrendingDay(language)
            _text.value = Response(isLoading = false, movieData = null, tvData = null, trendingDay = trending, trendingWeek = null, movieSearch = null, tvSearch = null, actorsSearch = null)
        }
    }

    fun getTrendingWeek(language: String) {
        viewModelScope.launch {
            _text.value = Response(isLoading = true, movieData = null, tvData = null, trendingDay = null, trendingWeek = null, movieSearch = null, tvSearch = null, actorsSearch = null)
            val trendingWeek = moviesDbRepository.getTrendingWeek(language)
            _text.value = Response(isLoading = false, movieData = null, tvData = null, trendingDay = null, trendingWeek = trendingWeek, movieSearch = null, tvSearch = null, actorsSearch = null)
        }
    }

    fun searchMovie(language: String, name: String) {
        viewModelScope.launch {
            _text.value = Response(isLoading = true, movieData = null, tvData = null, trendingDay = null, trendingWeek = null, movieSearch = null, tvSearch = null, actorsSearch = null)
            val searchMovie = moviesDbRepository.searchMovie(language, name)
            _text.value = Response(isLoading = false, movieData = null, tvData = null, trendingDay = null, trendingWeek = null, movieSearch = searchMovie, tvSearch = null, actorsSearch = null)
        }
    }

    fun searchTv(language: String, name: String) {
        viewModelScope.launch {
            _text.value = Response(isLoading = true, movieData = null, tvData = null, trendingDay = null, trendingWeek = null, movieSearch = null, tvSearch = null, actorsSearch = null)
            val searchTv = moviesDbRepository.searchTv(language, name)
            _text.value = Response(isLoading = false, movieData = null, tvData = null, trendingDay = null, trendingWeek = null, movieSearch = null, tvSearch = searchTv, actorsSearch = null)
        }
    }

    fun searchActors(language: String, name: String) {
        viewModelScope.launch {
            _text.value = Response(isLoading = true, movieData = null, tvData = null, trendingDay = null, trendingWeek = null, tvSearch = null, movieSearch = null, actorsSearch = null)
            val searchActors = moviesDbRepository.searchActors(language, name)
            _text.value = Response(isLoading = false, movieData = null, tvData = null, trendingDay = null, trendingWeek = null, tvSearch = null, movieSearch = null, actorsSearch = searchActors)
        }
    }
}