package com.example.moviesdb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.MoviesConvert
import com.example.network.MoviesDbRepository
import com.example.network.TrendingConvert
import com.example.network.TrendingWeekConvert
import com.example.network.TvConvert
import com.example.network.WrapperActors
import com.example.network.WrapperMovie
import com.example.network.WrapperTv
import com.example.network.asWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class Response(
    val isLoading: Boolean,
    val movieData: MoviesConvert?,
    val tvData: TvConvert?,
    val trendingDay: TrendingConvert?,
    val trendingWeek: TrendingWeekConvert?,
    val movieSearch: List<WrapperMovie>? = emptyList(),
    val tvSearch: List<WrapperTv>? = emptyList(),
    val actorsSearch: List<WrapperActors>? = emptyList()
)

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val moviesDbRepository : MoviesDbRepository
): ViewModel() {

    private val _text = MutableLiveData<Response>()
    val text: LiveData<Response> = _text

    private val _text_RatedTv = MutableLiveData<Response>()
    val text_RatedTv: LiveData<Response> = _text_RatedTv

    private val _text_TrendDay= MutableLiveData<Response>()
    val text_TrendDay: LiveData<Response> = _text_TrendDay

    private val _text_TrendWeek= MutableLiveData<Response>()
    val text_TrendWeek: LiveData<Response> = _text_TrendWeek

    private val _text_SearchMovie= MutableLiveData<Response>()
    val text_SearchMovie: LiveData<Response> = _text_SearchMovie

    private val _text_SearchTv= MutableLiveData<Response>()
    val text_SearchTv: LiveData<Response> = _text_SearchTv

    private val _text_SearchAct= MutableLiveData<Response>()
    val text_SearchAct: LiveData<Response> = _text_SearchAct

    fun getTopRatedMovies(language: String, page: Int) {
        viewModelScope.launch {
            _text.value = Response(isLoading = true, movieData = null, trendingDay = null, tvData = null, trendingWeek = null, movieSearch = null, tvSearch = null, actorsSearch = null)
            val movie = moviesDbRepository.getTopRatedMovies(language, page)
            _text.value = Response(isLoading = false, movieData = movie, trendingDay = null, tvData = null, trendingWeek = null, movieSearch = null, tvSearch = null, actorsSearch = null)
        }
    }

    fun getTopRatedTv(language: String, page: Int) {
        viewModelScope.launch {
            _text_RatedTv.value = Response(isLoading = true, movieData = null, tvData = null, trendingDay = null, trendingWeek = null, movieSearch = null, tvSearch = null, actorsSearch = null)
            val tv = moviesDbRepository.getTopRatedTv(language, page)
            _text_RatedTv.value = Response(isLoading = false, movieData = null, tvData = tv, trendingDay = null, trendingWeek = null, movieSearch = null, tvSearch = null, actorsSearch = null)
        }
    }

    fun getTrendingDay(language: String) {
        viewModelScope.launch {
            _text_TrendDay.value = Response(isLoading = true, movieData = null, tvData = null, trendingDay = null, trendingWeek = null, movieSearch = null, tvSearch = null, actorsSearch = null)
            val trending = moviesDbRepository.getTrendingDay(language)
            _text_TrendDay.value = Response(isLoading = false, movieData = null, tvData = null, trendingDay = trending, trendingWeek = null, movieSearch = null, tvSearch = null, actorsSearch = null)
        }
    }

    fun getTrendingWeek(language: String) {
        viewModelScope.launch {
            _text_TrendWeek.value = Response(isLoading = true, movieData = null, tvData = null, trendingDay = null, trendingWeek = null, movieSearch = null, tvSearch = null, actorsSearch = null)
            val trendingWeek = moviesDbRepository.getTrendingWeek(language)
            _text_TrendWeek.value = Response(isLoading = false, movieData = null, tvData = null, trendingDay = null, trendingWeek = trendingWeek, movieSearch = null, tvSearch = null, actorsSearch = null)
        }
    }

    fun searchMovie(language: String, name: String) {
        viewModelScope.launch {
            _text_SearchMovie.value = Response(isLoading = true, movieData = null, tvData = null, trendingDay = null, trendingWeek = null, movieSearch = null, tvSearch = null, actorsSearch = null)
            val searchMovie = moviesDbRepository.searchMovie(language, name)
                .asWrapper()
                .map { res ->
                    val isFavourite = moviesDbRepository.checkIfIsFavourite(res.search.id ?: -1)
                    res.copy(isFavourite = isFavourite == 1)
                }
            _text_SearchMovie.value = Response(isLoading = false, movieData = null, tvData = null, trendingDay = null, trendingWeek = null, movieSearch = searchMovie, tvSearch = null, actorsSearch = null)
        }
    }

    fun searchTv(language: String, name: String) {
        viewModelScope.launch {
            _text_SearchTv.value = Response(isLoading = true, movieData = null, tvData = null, trendingDay = null, trendingWeek = null, movieSearch = null, tvSearch = null, actorsSearch = null)
            val searchTv = moviesDbRepository.searchTv(language, name)
                .asWrapper()
                .map { res ->
                    val isFavourite = moviesDbRepository.checkIfIsFavourite(res.searchTv.id ?: -1)
                    res.copy(isFavourite = isFavourite == 1)
                }
            _text_SearchTv.value = Response(isLoading = false, movieData = null, tvData = null, trendingDay = null, trendingWeek = null, movieSearch = null, tvSearch = searchTv, actorsSearch = null)
        }
    }

    fun searchActors(language: String, name: String) {
        viewModelScope.launch {
            _text_SearchAct.value = Response(isLoading = true, movieData = null, tvData = null, trendingDay = null, trendingWeek = null, movieSearch = null, tvSearch = null, actorsSearch = null)
            val searchActors = moviesDbRepository.searchActors(language, name)
                .asWrapper()
                .map { res ->
                    val isFavourite = moviesDbRepository.checkIfIsFavourite(res.searchActors.id ?: -1)
                    res.copy(isFavourite = isFavourite == 1)
                }
            _text_SearchAct.value = Response(isLoading = false, movieData = null, tvData = null, trendingDay = null, trendingWeek = null, movieSearch = null, tvSearch = null, actorsSearch = searchActors)
        }
    }

    fun checkIfIsFavourite(id: Int) {
        viewModelScope.launch {
            val isFavourite = moviesDbRepository.checkIfIsFavourite(id)
            setToFavourite(id, isFavourite = isFavourite == 1)
        }
    }

    fun checkIfIsFavouriteTv(id: Int) {
        viewModelScope.launch {
            val isFavourite = moviesDbRepository.checkIfIsFavourite(id)
            setToFavouriteTv(id, isFavourite = isFavourite == 1)
        }
    }

    fun checkIfIsFavouriteAct(id: Int) {
        viewModelScope.launch {
            val isFavourite = moviesDbRepository.checkIfIsFavourite(id)
            setToFavouriteAct(id, isFavourite = isFavourite == 1)
        }
    }

    fun insertIntoDB(id: Int, name: String, release_date: String, posterPath: String, original_lang: String, overview: String, vote_avg: Float, typeMedia: String) {
        viewModelScope.launch {
            moviesDbRepository.insertIntoDB(id, name, release_date, posterPath, original_lang, overview, vote_avg, typeMedia)
            setToFavourite(id, true)
        }
    }

    fun insertIntoDBTv(id: Int, name: String, release_date: String, posterPath: String, original_lang: String, overview: String, vote_avg: Float, typeMedia: String) {
        viewModelScope.launch {
            moviesDbRepository.insertIntoDB(id, name, release_date, posterPath, original_lang, overview, vote_avg, typeMedia)
            setToFavouriteTv(id, true)
        }
    }

    fun insertIntoDBAct(id: Int, name: String, release_date: String, posterPath: String, original_lang: String, overview: String, vote_avg: Float, typeMedia: String) {
        viewModelScope.launch {
            moviesDbRepository.insertIntoDB(id, name, release_date, posterPath, original_lang, overview, vote_avg, typeMedia)
            setToFavouriteAct(id, true)
        }
    }

    private fun setToFavourite(id: Int, isFavourite: Boolean) {
        val newSearchMovie = _text_SearchMovie.value?.movieSearch.orEmpty()
            .map { res ->
                if (res.search.id == id) {
                    res.copy(isFavourite = isFavourite)
                } else {
                    res
                }
            }
        _text_SearchMovie.value = Response(
            isLoading = false,
            movieData = null,
            tvData = null,
            trendingDay = null,
            trendingWeek = null,
            movieSearch = newSearchMovie,
            tvSearch = null,
            actorsSearch = null
        )
    }

    private fun setToFavouriteTv(id: Int, isFavourite: Boolean) {
        val newSearchTv = _text_SearchTv.value?.tvSearch.orEmpty()
            .map { res ->
                if (res.searchTv.id == id) {
                    res.copy(isFavourite = isFavourite)
                } else {
                    res
                }
            }
        _text_SearchTv.value = Response(
            isLoading = false,
            movieData = null,
            tvData = null,
            trendingDay = null,
            trendingWeek = null,
            movieSearch = null,
            tvSearch = newSearchTv,
            actorsSearch = null
        )
    }

    private fun setToFavouriteAct(id: Int, isFavourite: Boolean) {
        val newSearchAct = _text_SearchAct.value?.actorsSearch.orEmpty()
            .map { res ->
                if (res.searchActors.id == id) {
                    res.copy(isFavourite = isFavourite)
                } else {
                    res
                }
            }
        _text_SearchAct.value = Response(
            isLoading = false,
            movieData = null,
            tvData = null,
            trendingDay = null,
            trendingWeek = null,
            movieSearch = null,
            tvSearch = null,
            actorsSearch = newSearchAct
        )
    }

    fun deleteMoviesFromDB(id: Int) {
        viewModelScope.launch {
            moviesDbRepository.deleteMoviesFromDB(id)
            setToFavourite(id, false)
        }
    }

    fun deleteTvFromDB(id: Int) {
        viewModelScope.launch {
            moviesDbRepository.deleteMoviesFromDB(id)
            setToFavouriteTv(id, false)
        }
    }

    fun deleteActorsFromDB(id: Int) {
        viewModelScope.launch {
            moviesDbRepository.deleteMoviesFromDB(id)
            setToFavouriteAct(id, false)
        }
    }
}