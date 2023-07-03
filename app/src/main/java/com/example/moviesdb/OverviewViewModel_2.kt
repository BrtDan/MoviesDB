package com.example.moviesdb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.MoviesConvert
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.network.MoviesDbRepository
import com.example.network.TvConvert
import kotlinx.coroutines.launch
import javax.inject.Inject

data class A(
    val isLoading: Boolean,
    val movieData: MoviesConvert?,
    val tvData: TvConvert?
)

@HiltViewModel
class OverviewViewModel_2 @Inject constructor(
    private val moviesDbRepository : MoviesDbRepository
): ViewModel() {

    private val _text = MutableLiveData<Response>()
    val text: LiveData<Response> = _text

    fun getTopRatedTv(language: String, page: Int) {
        viewModelScope.launch {
            _text.value = Response(isLoading = true, movieData = null, tvData = null)
            val tv = moviesDbRepository.getTopRatedTv(language, page)
            _text.value = Response(isLoading = false, movieData = null, tvData = tv)
        }
    }
}