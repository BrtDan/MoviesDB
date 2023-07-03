package com.example.topRated

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firstmoviestoprated.databinding.MovietopratedLayoutBinding
import com.squareup.picasso.Picasso

class movieTopRatedActivity : AppCompatActivity() {

    private val binding by lazy { MovietopratedLayoutBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val baseUrl = "https://image.tmdb.org/t/p/w500"
        val posterPath = intent.getStringExtra("urlImg")
        var imageUrl = "$baseUrl$posterPath"
        Picasso.get().load(imageUrl).into(binding.imgMovie)

        val originalTitle = intent.getStringExtra("originalTitle")
        val originalLang = intent.getStringExtra("originalLang")
        binding.titleOrigLang.text = "$originalTitle ($originalLang)"

        val releaseDate = intent.getStringExtra("releaseDate")
        binding.releaseDate.text = releaseDate

        val overview = intent.getStringExtra("overview")
        binding.overview.text = overview

        val vote_count = intent.getStringExtra("vote_count")
        binding.voteCount.text = "Vote: $vote_count"

        val voteAvg = intent.getStringExtra("voteAvg")
        binding.voteAvg.text = voteAvg

        binding.titleTextView.setOnClickListener{
            finish()
        }

        binding.goBack.setOnClickListener{
            finish()
        }

    }
}
