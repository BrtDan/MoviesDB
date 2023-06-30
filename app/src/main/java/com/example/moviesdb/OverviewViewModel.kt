package com.example.moviesdb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.MoviesConvert
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.network.MoviesDbRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

data class Response(
    val isLoading: Boolean,
    val data: MoviesConvert?
)

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val moviesDbRepository : MoviesDbRepository
): ViewModel() {

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    private val _text = MutableLiveData<Response>()

    val text: LiveData<Response> = _text

    fun getTopRatedMovies(language: String, page: Int){

        viewModelScope.launch{
            _text.value = Response(isLoading = true, null)
            val movie = moviesDbRepository.getTopRatedMovies(language, page)
            _text.value = Response(isLoading = false, movie)
        }
    }
}