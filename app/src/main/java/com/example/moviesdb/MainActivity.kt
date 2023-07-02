package com.example.moviesdb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviesdb.databinding.ActivityMainBinding
import androidx.activity.viewModels
import com.example.firstmoviestoprated.firstMoviesTopRatedActivity
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    val viewModel by viewModels<OverviewViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val language = "it-IT"

        viewModel.getTopRatedMovies(language, 1)

        viewModel.text.observe(this) {
            if (!it.isLoading) {
                binding.moviesName1.text = it.data?.results?.get(0)?.title
                binding.moviesName2.text = it.data?.results?.get(1)?.title
                binding.moviesName3.text = it.data?.results?.get(2)?.title
                var baseUrl = "https://image.tmdb.org/t/p/w500" // BASE URL immagini the movie db
                var posterPath = it.data?.results?.get(0)?.poster_path
                var imageUrl = "$baseUrl$posterPath"
                Picasso.get().load(imageUrl).into(binding.imageMovies1)
                posterPath = it.data?.results?.get(1)?.poster_path
                imageUrl = "$baseUrl$posterPath"
                Picasso.get().load(imageUrl).into(binding.imageMovies2)
                posterPath = it.data?.results?.get(2)?.poster_path
                imageUrl = "$baseUrl$posterPath"
                Picasso.get().load(imageUrl).into(binding.imageMovies3)

            }
        }

        binding.materialCardView1.setOnClickListener{
            val intent = Intent(this, firstMoviesTopRatedActivity::class.java)
            startActivity(intent)
        }

        /* CLICK SULL'IMG LIST */
        binding.list.setOnClickListener{

        }

        /* CLICK SULL'IMG SEARCH */
        binding.search.setOnClickListener{

        }
    }
}
