package com.example.topRated

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.MoviesDbRepository
import com.example.network.MoviesDetailsConvert
import com.example.network.TrendingConvert
import com.example.network.TrendingWeekConvert
import com.example.network.TrendingWeekDetailsConvert
import com.example.network.TvDetailsConvert
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


data class Response(
    val isLoading: Boolean?,
    val moviesData: MoviesDetailsConvert?,
    val tvsData: TvDetailsConvert?,
    val trendsWeek: TrendingWeekDetailsConvert?
)

@HiltViewModel
class moviesTopRatedViewModel @Inject constructor(
    private val moviesDbRepository : MoviesDbRepository
): ViewModel() {

    private val _text = MutableLiveData<Response>()
    val text: LiveData<Response> = _text

    fun getDetailsMovies(id: Int, language: String) {
        viewModelScope.launch {
            _text.value = Response(isLoading = true, moviesData = null, tvsData = null, trendsWeek = null)
            val movie = moviesDbRepository.getDetailsMovies(id, language)
            _text.value = Response(isLoading = false, moviesData = movie, tvsData = null, trendsWeek = null)
        }
    }

    fun getDetailsTv(id: Int, language: String) {
        viewModelScope.launch {
            _text.value = Response(isLoading = true, moviesData = null, tvsData = null, trendsWeek = null)
            val tv = moviesDbRepository.getDetailsTv(id, language)
            _text.value = Response(isLoading = false, moviesData = null, tvsData = tv, trendsWeek = null)
        }
    }
}