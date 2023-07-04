package com.example.moviesdb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.MoviesConvert
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.network.MoviesDbRepository
import com.example.network.TrendingConvert
import com.example.network.TrendingWeekConvert
import com.example.network.TvConvert
import kotlinx.coroutines.launch
import javax.inject.Inject

data class Response(
    val isLoading: Boolean,
    val movieData: MoviesConvert?,
    val tvData: TvConvert?,
    val trendingDay: TrendingConvert?,
    val trendingWeek: TrendingWeekConvert?
)

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val moviesDbRepository : MoviesDbRepository
): ViewModel() {

    private val _text = MutableLiveData<Response>()
    val text: LiveData<Response> = _text

    fun getTopRatedMovies(language: String, page: Int) {
        viewModelScope.launch {
            _text.value = Response(isLoading = true, movieData = null, trendingDay = null, tvData = null, trendingWeek = null)
            val movie = moviesDbRepository.getTopRatedMovies(language, page)
            _text.value = Response(isLoading = false, movieData = movie, trendingDay = null, tvData = null, trendingWeek = null)
        }
    }
}