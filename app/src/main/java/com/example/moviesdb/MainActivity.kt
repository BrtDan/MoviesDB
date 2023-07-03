package com.example.moviesdb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviesdb.databinding.ActivityMainBinding
import androidx.activity.viewModels
import com.example.topRated.movieTopRatedActivity
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    val viewModel by viewModels<OverviewViewModel>()
    val viewModel_2 by viewModels<OverviewViewModel_2>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val language = "it-IT"

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

        viewModel_2.getTopRatedTv(language, 1)

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

        /* CLICK SULL'IMG LIST */
        binding.list.setOnClickListener{

        }

        /* CLICK SULL'IMG SEARCH */
        binding.search.setOnClickListener{

        }
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
}