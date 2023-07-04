package com.example.actorstrendingweek

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.actorstrendingweek.databinding.ActorstrendingacitivityLayoutBinding
import com.squareup.picasso.Picasso

class actorTopRatedActivity : AppCompatActivity() {

    private val binding by lazy { ActorstrendingacitivityLayoutBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val urlImg : String? = intent.getStringExtra("urlImg")
        val baseUrl = "https://image.tmdb.org/t/p/w500"
        var imageUrl = "$baseUrl$urlImg"
        Picasso.get().load(imageUrl).into(binding.imgActors)

        val nameActors : String? = intent.getStringExtra("name")
        binding.nameActors.text = nameActors

        val title1 : String? = intent.getStringExtra("title1")
        val title2 : String? = intent.getStringExtra("title2")
        val title3 : String? = intent.getStringExtra("title3")

        val original_language1: String? = intent.getStringExtra("original_language1")
        val original_language2: String? = intent.getStringExtra("original_language2")
        val original_language3: String? = intent.getStringExtra("original_language3")

        val releaseDate1 : String? = intent.getStringExtra("releaseDate1")
        val releaseDate2 : String? = intent.getStringExtra("releaseDate2")
        val releaseDate3 : String? = intent.getStringExtra("releaseDate3")

        val overview1 : String? = intent.getStringExtra("overview1")
        val overview2 : String? = intent.getStringExtra("overview2")
        val overview3 : String? = intent.getStringExtra("overview3")

        val vote_average1 : String? = intent.getStringExtra("vote_average1")
        val formattedVoteAvg1 = String.format("%.1f", vote_average1?.toFloatOrNull() ?: 0.0f)
        val vote_average2 : String? = intent.getStringExtra("vote_average2")
        val formattedVoteAvg2 = String.format("%.1f", vote_average2?.toFloatOrNull() ?: 0.0f)
        val vote_average3 : String? = intent.getStringExtra("vote_average3")
        val formattedVoteAvg3 = String.format("%.1f", vote_average3?.toFloatOrNull() ?: 0.0f)

        binding.knownFor.text = "-$title1 ($original_language1) $releaseDate1\n\n$overview1\n\n$formattedVoteAvg1/10" +
                "\n\n\n- $title2 ($original_language2) $releaseDate2\n\n$overview2\n\n$formattedVoteAvg2/10" +
                "\n\n\n- $title3 ($original_language3) $releaseDate3\n\n$overview3\n\n$formattedVoteAvg3/10"

        binding.titleTextView.setOnClickListener{
            finish()
        }

        binding.goBack.setOnClickListener{
            finish()
        }

    }
}
