package com.example.moviesdb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import com.example.moviesdb.databinding.ActivityMainBinding
import androidx.activity.viewModels
import com.example.actorstrendingweek.actorTopRatedActivity
import com.example.topRated.movieTopRatedActivity
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    val viewModel by viewModels<OverviewViewModel>()
    val viewModel_2 by viewModels<OverviewViewModel_2>()
    val viewModel_3 by viewModels<OverviewViewModel_3>()
    val viewModel_4 by viewModels<OverviewViewModel_4>()
    val viewModel_5 by viewModels<OverviewViewModel_5>()
    val viewModel_6 by viewModels<OverviewViewModel_6>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.searchBar.visibility = View.GONE
        var state = "close"

        val language = "en-US"

        viewModel.getTopRatedMovies(language, 1)

        viewModel.text.observe(this) {
            binding.moviesName1.text = it.movieData?.results?.get(0)?.title
            binding.moviesName2.text = it.movieData?.results?.get(1)?.title
            binding.moviesName3.text = it.movieData?.results?.get(2)?.title
            val baseUrl = "https://image.tmdb.org/t/p/w500" // BASE URL immagini tmdb
            var posterPath = it.movieData?.results?.get(0)?.poster_path
            var imageUrl = "$baseUrl$posterPath"
            Picasso.get().load(imageUrl).into(binding.imageMovies1)
            posterPath = it.movieData?.results?.get(1)?.poster_path
            imageUrl = "$baseUrl$posterPath"
            Picasso.get().load(imageUrl).into(binding.imageMovies2)
            posterPath = it.movieData?.results?.get(2)?.poster_path
            imageUrl = "$baseUrl$posterPath"
            Picasso.get().load(imageUrl).into(binding.imageMovies3)

            binding.imageMovies1.setOnClickListener{
                putExtra(0)
            }
            binding.imageMovies2.setOnClickListener{
                putExtra(1)
            }
            binding.imageMovies3.setOnClickListener{
                putExtra(2)
            }
        }

        val coroutineScope = CoroutineScope(Dispatchers.Main)
        coroutineScope.launch {
            viewModel_2.getTopRatedTv(language, 1)
        }


        viewModel_2.text.observe(this){
            binding.tvName1.text = it.tvData?.results?.get(0)?.name
            binding.tvName2.text = it.tvData?.results?.get(1)?.name
            binding.tvName3.text = it.tvData?.results?.get(2)?.name
            val baseUrl = "https://image.tmdb.org/t/p/w500" // BASE URL immagini tmdb
            var posterPath = it.tvData?.results?.get(0)?.poster_path
            var imageUrl = "$baseUrl$posterPath"
            Picasso.get().load(imageUrl).into(binding.imageTv1)
            posterPath = it.tvData?.results?.get(1)?.poster_path
            imageUrl = "$baseUrl$posterPath"
            Picasso.get().load(imageUrl).into(binding.imageTv2)
            posterPath = it.tvData?.results?.get(2)?.poster_path
            imageUrl = "$baseUrl$posterPath"
            Picasso.get().load(imageUrl).into(binding.imageTv3)

            binding.imageTv1.setOnClickListener{
                putExtra_tv(0)
            }
            binding.imageTv2.setOnClickListener{
                putExtra_tv(1)
            }
            binding.imageTv3.setOnClickListener{
                putExtra_tv(2)
            }
        }

        viewModel_3.getTrendingDay(language, 1)

        viewModel_3.text.observe(this){

            binding.trendingName1.text = it.trendingDay?.results?.get(0)?.nameTitle
            binding.trendingName2.text = it.trendingDay?.results?.get(1)?.nameTitle
            binding.trendingName3.text = it.trendingDay?.results?.get(2)?.nameTitle
            binding.trendingName4.text = it.trendingDay?.results?.get(3)?.nameTitle
            binding.trendingName5.text = it.trendingDay?.results?.get(4)?.nameTitle

            val baseUrl = "https://image.tmdb.org/t/p/w500" // BASE URL immagini tmdb
            var posterPath = it.trendingDay?.results?.get(0)?.poster_path
            var imageUrl = "$baseUrl$posterPath"
            Picasso.get().load(imageUrl).into(binding.imageTrending1)
            posterPath = it.trendingDay?.results?.get(1)?.poster_path
            imageUrl = "$baseUrl$posterPath"
            Picasso.get().load(imageUrl).into(binding.imageTrending2)
            posterPath = it.trendingDay?.results?.get(2)?.poster_path
            imageUrl = "$baseUrl$posterPath"
            Picasso.get().load(imageUrl).into(binding.imageTrending3)
            posterPath = it.trendingDay?.results?.get(3)?.poster_path
            imageUrl = "$baseUrl$posterPath"
            Picasso.get().load(imageUrl).into(binding.imageTrending4)
            posterPath = it.trendingDay?.results?.get(4)?.poster_path
            imageUrl = "$baseUrl$posterPath"
            Picasso.get().load(imageUrl).into(binding.imageTrending5)

            binding.imageTrending1.setOnClickListener{
                putExtra_trending(0)
            }
            binding.imageTrending2.setOnClickListener{
                putExtra_trending(1)
            }
            binding.imageTrending3.setOnClickListener{
                putExtra_trending(2)
            }
            binding.imageTrending4.setOnClickListener{
                putExtra_trending(3)
            }
            binding.imageTrending5.setOnClickListener{
                putExtra_trending(4)
            }
        }

        viewModel_4.getTrendingWeek(language)
        viewModel_4.text.observe(this){
            binding.trendingWeekName1.text = it.trendingWeek?.results?.get(0)?.name
            binding.trendingWeekName2.text = it.trendingWeek?.results?.get(1)?.name
            binding.trendingWeekName3.text = it.trendingWeek?.results?.get(5)?.name
            binding.trendingWeekName4.text = it.trendingWeek?.results?.get(3)?.name
            binding.trendingWeekName5.text = it.trendingWeek?.results?.get(4)?.name

            val baseUrl = "https://image.tmdb.org/t/p/w500" // BASE URL immagini tmdb
            var posterPath = it.trendingWeek?.results?.get(0)?.profile_path
            var imageUrl = "$baseUrl$posterPath"
            Picasso.get().load(imageUrl).into(binding.imageTrendingWeek1)
            posterPath = it.trendingWeek?.results?.get(1)?.profile_path
            imageUrl = "$baseUrl$posterPath"
            Picasso.get().load(imageUrl).into(binding.imageTrendingWeek2)
            posterPath = it.trendingWeek?.results?.get(5)?.profile_path
            imageUrl = "$baseUrl$posterPath"
            Picasso.get().load(imageUrl).into(binding.imageTrendingWeek3)
            posterPath = it.trendingWeek?.results?.get(3)?.profile_path
            imageUrl = "$baseUrl$posterPath"
            Picasso.get().load(imageUrl).into(binding.imageTrendingWeek4)
            posterPath = it.trendingWeek?.results?.get(4)?.profile_path
            imageUrl = "$baseUrl$posterPath"
            Picasso.get().load(imageUrl).into(binding.imageTrendingWeek5)

            binding.imageTrendingWeek1.setOnClickListener{
                putExtra_trendingWeek(0)
            }
            binding.imageTrendingWeek2.setOnClickListener{
                putExtra_trendingWeek(1)
            }
            binding.imageTrendingWeek3.setOnClickListener{
                putExtra_trendingWeek(5)
            }
            binding.imageTrendingWeek4.setOnClickListener{
                putExtra_trendingWeek(3)
            }
            binding.imageTrendingWeek5.setOnClickListener{
                putExtra_trendingWeek(4)
            }
        }

        /* CLICK SULL'IMG LIST */
        binding.list.setOnClickListener{
            if(state == "open"){
                binding.body.visibility = View.VISIBLE
                binding.search.visibility = View.VISIBLE
                binding.searchBar.visibility = View.GONE
                binding.list.setImageResource(R.drawable.baseline_tune_white_48)
//                binding.searchBarText.text = ""
                state = "close"
            }
        }

        /* CLICK SULL'IMG SEARCH */
        binding.search.setOnClickListener{
            binding.body.visibility = View.GONE
            binding.search.visibility = View.GONE
            binding.list.setImageResource(R.drawable.baseline_close_white_48)
            binding.searchBar.visibility = View.VISIBLE
            state = "open"
        }

        binding.searchBarText.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if(binding.radioMovies.isChecked){
                    viewModel_5.searchMovie(language, query)
                } else{
                    viewModel_6.searchTv(language, query)
                }
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

    }

    fun putExtra(n: Int){
        viewModel.text.observe(this) {
            val intent = Intent(this, movieTopRatedActivity::class.java)
            intent.putExtra("originalTitle", it.movieData?.results?.get(n)?.title.toString())
            intent.putExtra("originalLang", it.movieData?.results?.get(n)?.original_language.toString())
            intent.putExtra("releaseDate", it.movieData?.results?.get(n)?.release_date.toString())
            intent.putExtra("overview", it.movieData?.results?.get(n)?.overview.toString())
            intent.putExtra("vote_count", it.movieData?.results?.get(n)?.vote_count.toString())
            intent.putExtra("voteAvg", it.movieData?.results?.get(n)?.vote_average.toString())
            intent.putExtra("urlImg", it.movieData?.results?.get(n)?.poster_path)
            startActivity(intent)
        }
    }

    fun putExtra_tv(n: Int){
        viewModel_2.text.observe(this) {
            val intent = Intent(this, movieTopRatedActivity::class.java)
            intent.putExtra("originalTitle", it.tvData?.results?.get(n)?.name.toString())
            intent.putExtra("originalLang", it.tvData?.results?.get(n)?.original_language.toString())
            intent.putExtra("releaseDate", it.tvData?.results?.get(n)?.first_air_date.toString())
            intent.putExtra("overview", it.tvData?.results?.get(n)?.overview.toString())
            intent.putExtra("vote_count", it.tvData?.results?.get(n)?.vote_count.toString())
            intent.putExtra("voteAvg", it.tvData?.results?.get(n)?.vote_average.toString())
            intent.putExtra("urlImg", it.tvData?.results?.get(n)?.poster_path)
            startActivity(intent)
        }
    }

    fun putExtra_trending(n: Int){
        viewModel_3.text.observe(this) {
            val intent = Intent(this, movieTopRatedActivity::class.java)
            intent.putExtra("originalTitle", it.trendingDay?.results?.get(n)?.nameTitle.toString())
            intent.putExtra("originalLang", it.trendingDay?.results?.get(n)?.original_language.toString())
            intent.putExtra("releaseDate", it.trendingDay?.results?.get(n)?.releaseDate.toString())
            intent.putExtra("overview", it.trendingDay?.results?.get(n)?.overview.toString())
            intent.putExtra("vote_count", it.trendingDay?.results?.get(n)?.vote_count.toString())
            intent.putExtra("voteAvg", it.trendingDay?.results?.get(n)?.vote_average.toString())
            intent.putExtra("urlImg", it.trendingDay?.results?.get(n)?.poster_path)
            startActivity(intent)
        }
    }

    fun putExtra_trendingWeek(n: Int){
        viewModel_4.text.observe(this) {
            val intent = Intent(this, actorTopRatedActivity::class.java)
            intent.putExtra("name", it.trendingWeek?.results?.get(n)?.name.toString())
            intent.putExtra("title1", it.trendingWeek?.results?.get(n)?.known_for?.get(0)?.title.toString())
            intent.putExtra("title2", it.trendingWeek?.results?.get(n)?.known_for?.get(1)?.title.toString())
            intent.putExtra("title3", it.trendingWeek?.results?.get(n)?.known_for?.get(2)?.title.toString())
            intent.putExtra("releaseDate1", it.trendingWeek?.results?.get(n)?.known_for?.get(0)?.release_date.toString())
            intent.putExtra("releaseDate2", it.trendingWeek?.results?.get(n)?.known_for?.get(1)?.release_date.toString())
            intent.putExtra("releaseDate3", it.trendingWeek?.results?.get(n)?.known_for?.get(2)?.release_date.toString())
            intent.putExtra("overview1", it.trendingWeek?.results?.get(n)?.known_for?.get(0)?.overview.toString())
            intent.putExtra("overview2", it.trendingWeek?.results?.get(n)?.known_for?.get(1)?.overview.toString())
            intent.putExtra("overview3", it.trendingWeek?.results?.get(n)?.known_for?.get(2)?.overview.toString())
            intent.putExtra("urlImg", it.trendingWeek?.results?.get(n)?.profile_path)
            intent.putExtra("vote_average1", it.trendingWeek?.results?.get(n)?.known_for?.get(0)?.vote_average.toString())
            intent.putExtra("vote_average2", it.trendingWeek?.results?.get(n)?.known_for?.get(1)?.vote_average.toString())
            intent.putExtra("vote_average3", it.trendingWeek?.results?.get(n)?.known_for?.get(2)?.vote_average.toString())
            intent.putExtra("original_language1", it.trendingWeek?.results?.get(n)?.known_for?.get(0)?.original_language.toString())
            intent.putExtra("original_language2", it.trendingWeek?.results?.get(n)?.known_for?.get(1)?.original_language.toString())
            intent.putExtra("original_language3", it.trendingWeek?.results?.get(n)?.known_for?.get(2)?.original_language.toString())
            startActivity(intent)
        }
    }
}