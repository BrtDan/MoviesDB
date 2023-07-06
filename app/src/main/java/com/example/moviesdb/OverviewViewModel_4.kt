package com.example.moviesdb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.network.MoviesDbRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel_4 @Inject constructor(
    private val moviesDbRepository : MoviesDbRepository
): ViewModel() {

    private val _text = MutableLiveData<Response>()
    val text: LiveData<Response> = _text

    fun getTrendingWeek(language: String) {
        viewModelScope.launch {
            _text.value = Response(isLoading = true, movieData = null, tvData = null, trendingDay = null, trendingWeek = null, movieSearch = null, tvSearch = null, actorsSearch = null)
            val trendingWeek = moviesDbRepository.getTrendingWeek(language)
            _text.value = Response(isLoading = false, movieData = null, tvData = null, trendingDay = null, trendingWeek = trendingWeek, movieSearch = null, tvSearch = null, actorsSearch = null)
        }
    }
}