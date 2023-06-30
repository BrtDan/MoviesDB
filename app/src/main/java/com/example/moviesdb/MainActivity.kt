package com.example.moviesdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviesdb.databinding.ActivityMainBinding
import androidx.activity.viewModels
import com.example.network.MoviesConvert
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONException
import org.json.JSONObject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    val viewModel by viewModels<OverviewViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val language = "it-IT" // binding.lang.text

        viewModel.getTopRatedMovies(language, 1)

        viewModel.text.observe(this) {
            if (!it.isLoading) {
                binding.moviesName1.text = it.data?.results?.get(0)?.title
                binding.moviesName2.text = it.data?.results?.get(1)?.title
                binding.moviesName3.text = it.data?.results?.get(2)?.title
                binding.imageMovies1. = it.data?.results?.get(0)?.poster_path // FIXME: AGGIUSTARE IMMAGINE PER I TOP RATED MOVIES 
            }
        }
        /* CLICK SULL'IMG LIST */
        binding.list.setOnClickListener{

        }

        /* CLICK SULL'IMG SEARCH */
        binding.search.setOnClickListener{

        }
    }
}
