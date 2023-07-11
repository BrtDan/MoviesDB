package com.example.topRated

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.firstmoviestoprated.R
import com.example.firstmoviestoprated.databinding.MovietopratedLayoutBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class movieTopRatedActivity : AppCompatActivity() {

    private val binding by lazy { MovietopratedLayoutBinding.inflate(layoutInflater) }
    val viewModel by viewModels<moviesTopRatedViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val type : String? = intent.getStringExtra("type")

        val id : Int = intent.getStringExtra("id").toString().toInt()
        val language : String = intent.getStringExtra("language").toString()

        if(type == "movie"){
            println("MOVIE")
            viewModel.getDetailsMovies(id, language)
            viewModel.text.observe(this){
                binding.imageView.visibility = View.VISIBLE
                val baseUrl = "https://image.tmdb.org/t/p/w500"
                val posterPath = it.moviesData?.poster_path
                var imageUrl = "$baseUrl$posterPath"
                Picasso.get().load(imageUrl).into(binding.imgMovie)

                val originalTitle = it.moviesData?.original_title
                val originalLang = it.moviesData?.original_language
                binding.titleOrigLang.text = "$originalTitle ($originalLang)"

                binding.releaseDate.text = it.moviesData?.release_date
                binding.overview.text = it.moviesData?.overview

                val vote_count = it.moviesData?.vote_count
                binding.voteCount.text = "Vote: $vote_count"

                val voteAvg = it.moviesData?.vote_average
                val formattedVoteAvg = String.format("%.1f", voteAvg?.toFloat() ?: 0.0f)
                binding.voteAvg.text = formattedVoteAvg
            }
        } else if (type == "tv"){
            println("TV")
            binding.imageView.visibility = View.VISIBLE
            viewModel.getDetailsTv(id, language)
            viewModel.textTv.observe(this){
                val baseUrl = "https://image.tmdb.org/t/p/w500"
                val posterPath = it.tvsData?.poster_path
                var imageUrl = "$baseUrl$posterPath"
                Picasso.get().load(imageUrl).into(binding.imgMovie)

                val originalTitle = it.tvsData?.original_name
                val originalLang = it.tvsData?.original_language
                binding.titleOrigLang.text = "$originalTitle ($originalLang)"

                binding.releaseDate.text = it.tvsData?.first_air_date
                binding.overview.text = it.tvsData?.overview

                val vote_count = it.tvsData?.vote_count
                binding.voteCount.text = "Vote: $vote_count"

                val voteAvg = it.tvsData?.vote_average
                val formattedVoteAvg = String.format("%.1f", voteAvg?.toFloat() ?: 0.0f)
                binding.voteAvg.text = formattedVoteAvg
            }
        } else if (type == "trendDay"){
            println("TREND DAY")
            binding.imageView.visibility = View.VISIBLE
            if(intent.getStringExtra("typeTrend").toString() == "movie"){
                viewModel.getDetailsMovies(id, language)
                viewModel.text.observe(this){
                    val baseUrl = "https://image.tmdb.org/t/p/w500"
                    val posterPath = it.moviesData?.poster_path
                    var imageUrl = "$baseUrl$posterPath"
                    Picasso.get().load(imageUrl).into(binding.imgMovie)

                    val originalTitle = it.moviesData?.original_title
                    val originalLang = it.moviesData?.original_language
                    binding.titleOrigLang.text = "$originalTitle ($originalLang)"

                    binding.releaseDate.text = it.moviesData?.release_date
                    binding.overview.text = it.moviesData?.overview

                    val vote_count = it.moviesData?.vote_count
                    binding.voteCount.text = "Vote: $vote_count"

                    val voteAvg = it.moviesData?.vote_average
                    val formattedVoteAvg = String.format("%.1f", voteAvg?.toFloat() ?: 0.0f)
                    binding.voteAvg.text = formattedVoteAvg
                }
            } else{
                binding.imageView.visibility = View.VISIBLE
                viewModel.getDetailsTv(id, language)
                viewModel.textTv.observe(this){
                    val baseUrl = "https://image.tmdb.org/t/p/w500"
                    val posterPath = it.tvsData?.poster_path
                    var imageUrl = "$baseUrl$posterPath"
                    Picasso.get().load(imageUrl).placeholder(R.drawable.placeholder_view).error(R.drawable.placeholder_view).into(binding.imgMovie)

                    val originalTitle = it.tvsData?.original_name
                    val originalLang = it.tvsData?.original_language
                    binding.titleOrigLang.text = "$originalTitle ($originalLang)"

                    binding.releaseDate.text = it.tvsData?.first_air_date
                    binding.overview.text = it.tvsData?.overview

                    val vote_count = it.tvsData?.vote_count
                    binding.voteCount.text = "Vote: $vote_count"

                    val voteAvg = it.tvsData?.vote_average
                    val formattedVoteAvg = String.format("%.1f", voteAvg?.toFloat() ?: 0.0f)
                    binding.voteAvg.text = formattedVoteAvg
                }
            }
        }
        binding.titleTextView.setOnClickListener{
            finish()
        }

        binding.goBack.setOnClickListener{
            finish()
        }

    }
}
