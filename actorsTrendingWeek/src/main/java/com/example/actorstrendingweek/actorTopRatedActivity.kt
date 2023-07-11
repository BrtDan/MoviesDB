package com.example.actorstrendingweek

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.actorstrendingweek.databinding.ActorstrendingacitivityLayoutBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class actorTopRatedActivity : AppCompatActivity() {

    private val binding by lazy { ActorstrendingacitivityLayoutBinding.inflate(layoutInflater) }
    val viewModel by viewModels<actorTopRatedViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val id : Int = intent.getStringExtra("id").toString().toInt()
        val language : String = intent.getStringExtra("language").toString()

        viewModel.getDetailsTrendingWeek(id, language)
        viewModel.text.observe(this){
            val baseUrl = "https://image.tmdb.org/t/p/w500"
            val posterPath = it.trendsWeek?.profile_path
            var imageUrl = "$baseUrl$posterPath"
            Picasso.get().load(imageUrl).placeholder(R.drawable.placeholder_view).error(R.drawable.placeholder_view).into(binding.imgActors)

            binding.nameActors.text = it.trendsWeek?.name
            binding.knownFor.text = it.trendsWeek?.biography
        }

        binding.titleTextView.setOnClickListener{
            finish()
        }

        binding.goBack.setOnClickListener{
            finish()
        }

    }
}
