package com.example.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.MoviesDB
import com.example.network.MoviesDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


data class Response(
    val isLoading: Boolean,
    val data: List<MoviesDB>?
)

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val moviesDbRepository : MoviesDbRepository
): ViewModel() {

    private val _text = MutableLiveData<Response>()
    val text: LiveData<Response> = _text

    fun getDataFromDb(){
        viewModelScope.launch {
            _text.value = Response(isLoading = true, data = null)
            val data = moviesDbRepository.getDataFromDB()
            _text.value = Response(isLoading = false, data = data)
        }
    }

}
