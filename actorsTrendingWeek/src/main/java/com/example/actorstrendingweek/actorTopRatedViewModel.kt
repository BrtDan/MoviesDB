package com.example.actorstrendingweek

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.network.MoviesDbRepository
import com.example.network.TrendingWeekDetailsConvert
import kotlinx.coroutines.launch
import javax.inject.Inject

data class Response(
    val isLoading: Boolean,
    val trendsWeek: TrendingWeekDetailsConvert?
)

@HiltViewModel
class actorTopRatedViewModel @Inject constructor(
    private val moviesDbRepository : MoviesDbRepository
): ViewModel() {

    private val _text = MutableLiveData<Response>()
    val text: LiveData<Response> = _text

    fun getDetailsTrendingWeek(id: Int, language: String) {
        viewModelScope.launch {
            _text.value = Response(isLoading = true, trendsWeek = null)
            val trendingWeek = moviesDbRepository.getDetailsTrendingWeek(id, language)
            _text.value = Response(isLoading = false, trendsWeek = trendingWeek)
        }
    }
}