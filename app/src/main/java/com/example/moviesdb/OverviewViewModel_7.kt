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
class OverviewViewModel_7 @Inject constructor(
    private val moviesDbRepository : MoviesDbRepository
): ViewModel() {

    private val _text = MutableLiveData<Response>()
    val text: LiveData<Response> = _text

    fun searchActors(language: String, name: String) {
        viewModelScope.launch {
            _text.value = Response(isLoading = true, movieData = null, tvData = null, trendingDay = null, trendingWeek = null, tvSearch = null, movieSearch = null, actorsSearch = null)
            val searchActors = moviesDbRepository.searchActors(language, name)
            _text.value = Response(isLoading = false, movieData = null, tvData = null, trendingDay = null, trendingWeek = null, tvSearch = null, movieSearch = null, actorsSearch = searchActors)
        }
    }
}