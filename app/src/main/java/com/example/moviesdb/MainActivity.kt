package com.example.moviesdb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import com.example.moviesdb.databinding.ActivityMainBinding
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.actorstrendingweek.actorTopRatedActivity
import com.example.settings.settingActivity
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

    private val adapter by lazy { SearchAdapter() }
    private val adapter2 by lazy { SearchAdapterTv() }
    private val adapter3 by lazy { SearchAdapterActors() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.searchBar.visibility = View.GONE
        var state = "close"

        val language = "en-US"

        /*      TOP RATED MOVIES        */

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
                putExtraMoviesDetails(0, language)
            }
            binding.imageMovies2.setOnClickListener{
                putExtraMoviesDetails(1, language)
            }
            binding.imageMovies3.setOnClickListener{
                putExtraMoviesDetails(2, language)
            }
        }

        /*      TOP RATED TV SERIES     */

        val coroutineScope = CoroutineScope(Dispatchers.Main)
        coroutineScope.launch {
            viewModel.getTopRatedTv(language, 1)
        }


        viewModel.text.observe(this){
            binding.tvName1.text = it.tvData?.results?.get(0)?.original_name
            binding.tvName2.text = it.tvData?.results?.get(1)?.original_name
            binding.tvName3.text = it.tvData?.results?.get(2)?.original_name
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
                putExtraTvDetails(0, language)
            }
            binding.imageTv2.setOnClickListener{
                putExtraTvDetails(1, language)
            }
            binding.imageTv3.setOnClickListener{
                putExtraTvDetails(2, language)
            }
        }

        /*      TRENDING OF THE DAY     */

        viewModel.getTrendingDay(language)

        viewModel.text.observe(this){

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
                putExtraTrendingDetails(0, language)
            }
            binding.imageTrending2.setOnClickListener{
                putExtraTrendingDetails(1, language)
            }
            binding.imageTrending3.setOnClickListener{
                putExtraTrendingDetails(2, language)
            }
            binding.imageTrending4.setOnClickListener{
                putExtraTrendingDetails(3, language)
            }
            binding.imageTrending5.setOnClickListener{
                putExtraTrendingDetails(4, language)
            }
        }

        /*    TRENDING ACTORS OF THE WEEK     */

        viewModel.getTrendingWeek(language)
        viewModel.text.observe(this){
            binding.trendingWeekName1.text = it.trendingWeek?.results?.get(0)?.name
            binding.trendingWeekName2.text = it.trendingWeek?.results?.get(1)?.name
            binding.trendingWeekName3.text = it.trendingWeek?.results?.get(2)?.name
            binding.trendingWeekName4.text = it.trendingWeek?.results?.get(3)?.name
            binding.trendingWeekName5.text = it.trendingWeek?.results?.get(4)?.name

            val baseUrl = "https://image.tmdb.org/t/p/w500" // BASE URL immagini tmdb
            var posterPath = it.trendingWeek?.results?.get(0)?.profile_path
            var imageUrl = "$baseUrl$posterPath"
            Picasso.get().load(imageUrl).into(binding.imageTrendingWeek1)
            posterPath = it.trendingWeek?.results?.get(1)?.profile_path
            imageUrl = "$baseUrl$posterPath"
            Picasso.get().load(imageUrl).into(binding.imageTrendingWeek2)
            posterPath = it.trendingWeek?.results?.get(2)?.profile_path
            imageUrl = "$baseUrl$posterPath"
            Picasso.get().load(imageUrl).into(binding.imageTrendingWeek3)
            posterPath = it.trendingWeek?.results?.get(3)?.profile_path
            imageUrl = "$baseUrl$posterPath"
            Picasso.get().load(imageUrl).into(binding.imageTrendingWeek4)
            posterPath = it.trendingWeek?.results?.get(4)?.profile_path
            imageUrl = "$baseUrl$posterPath"
            Picasso.get().load(imageUrl).into(binding.imageTrendingWeek5)

            binding.imageTrendingWeek1.setOnClickListener{
                putExtraTrendingWeekDetails(0, language)
            }
            binding.imageTrendingWeek2.setOnClickListener{
                putExtraTrendingWeekDetails(1, language)
            }
            binding.imageTrendingWeek3.setOnClickListener{
                putExtraTrendingWeekDetails(2, language)
            }
            binding.imageTrendingWeek4.setOnClickListener{
                putExtraTrendingWeekDetails(3, language)
            }
            binding.imageTrendingWeek5.setOnClickListener{
                putExtraTrendingWeekDetails(4, language)
            }
        }

        /* CLICK SULL'IMG LIST */
        binding.list.setOnClickListener{
            if(state == "open"){
                binding.list.setImageResource(R.drawable.baseline_tune_white_48)
                binding.body.visibility = View.VISIBLE
                binding.searchBar.visibility = View.GONE
                state = "close"
            } else {
                val intent = Intent(this, settingActivity::class.java)
                startActivity(intent)
            }

        }

        /* CLICK SULL'IMG SEARCH */
        binding.search.setOnClickListener{
            binding.list.setImageResource(R.drawable.baseline_close_white_48)
            binding.body.visibility = View.GONE
            binding.searchBar.visibility = View.VISIBLE
            state = "open"
        }

        binding.searchItem.adapter = adapter
        binding.searchItem.layoutManager = LinearLayoutManager(this@MainActivity)

        binding.searchTv.adapter = adapter2
        binding.searchTv.layoutManager = LinearLayoutManager(this@MainActivity)

        binding.searchActors.adapter = adapter3
        binding.searchActors.layoutManager = LinearLayoutManager(this@MainActivity)

        binding.searchBarText.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if(binding.radioMovies.isChecked){
                    viewModel.searchMovie(query, language)
                    viewModel.text.observe(this@MainActivity){
                        binding.searchTv.visibility = View.GONE
                        binding.searchItem.visibility = View.VISIBLE
                        binding.searchActors.visibility = View.GONE
                        val searchList = it.movieSearch?.results
                        adapter.submitList(searchList)
                    }
                } else if (binding.radioTvSeries.isChecked){
                    viewModel.searchTv(query, language)
                    viewModel.text.observe(this@MainActivity){
                        binding.searchItem.visibility = View.GONE
                        binding.searchTv.visibility = View.VISIBLE
                        binding.searchActors.visibility = View.GONE
                        val searchList = it.tvSearch?.results
                        adapter2.submitList(searchList)
                    }
                } else {
                    viewModel.searchActors(query, language)
                    viewModel.text.observe(this@MainActivity){
                        binding.searchTv.visibility = View.GONE
                        binding.searchItem.visibility = View.GONE
                        binding.searchActors.visibility = View.VISIBLE
                        val searchList = it.actorsSearch?.results
                        adapter3.submitList(searchList)
                    }
                }
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

    }

    fun putExtraMoviesDetails(n: Int, language: String){
        viewModel.text.observe(this) {
            val intent = Intent(this, movieTopRatedActivity::class.java)
            intent.putExtra("id", it.movieData?.results?.get(n)?.id.toString())
            intent.putExtra("language", language)
            intent.putExtra("type", "movie")
            startActivity(intent)
        }
    }

    fun putExtraTvDetails(n: Int, language: String){
        viewModel.text.observe(this) {
            val intent = Intent(this, movieTopRatedActivity::class.java)
            intent.putExtra("id", it.tvData?.results?.get(n)?.id.toString())
            intent.putExtra("language", language)
            intent.putExtra("type", "tv")
            startActivity(intent)
        }
    }

    fun putExtraTrendingDetails(n: Int, language: String){
        viewModel.text.observe(this) {
            val intent = Intent(this, movieTopRatedActivity::class.java)
            intent.putExtra("id", it.trendingDay?.results?.get(n)?.id.toString())
            intent.putExtra("language", language)
            intent.putExtra("type", "trendDay")
            intent.putExtra("typeTrend", it.trendingDay?.results?.get(n)?.media_type)
            startActivity(intent)
        }
    }

    fun putExtraTrendingWeekDetails(n: Int, language: String){
        viewModel.text.observe(this) {
            val intent = Intent(this, actorTopRatedActivity::class.java)
            intent.putExtra("id", it.trendingWeek?.results?.get(n)?.id.toString())
            intent.putExtra("language", language)
            intent.putExtra("type", "trendWeek")
            startActivity(intent)
        }
    }
}