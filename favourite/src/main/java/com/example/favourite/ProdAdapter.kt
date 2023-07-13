package com.example.favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.favourite.databinding.AllprodBinding
import com.example.network.MoviesDB
import com.squareup.picasso.Picasso

class ProdAdapter : ListAdapter<MoviesDB, ProdAdapter.ProdViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MoviesDB>() {
            override fun areItemsTheSame(oldItem: MoviesDB, newItem: MoviesDB): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MoviesDB, newItem: MoviesDB): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdViewHolder {
        val binding = AllprodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProdViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProdViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ProdViewHolder(private val binding: AllprodBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MoviesDB) {

            binding.name.text = movie.name

            val release_date = movie.release_date
            val original_lang = movie.original_lang
            val overview = movie.overview

            val vote_avg = movie.vote_avg
            val formattedVoteAvg = String.format("%.1f", vote_avg ?: 0.0f)

            binding.dataLangOverviewVote.text = "$release_date ($original_lang) | $formattedVoteAvg/10\n\n$overview"
            val baseUrl = "https://image.tmdb.org/t/p/w500"
            val posterPath = movie.poster_path
            var imageUrl = "$baseUrl$posterPath"
            Picasso.get().load(imageUrl).placeholder(R.drawable.placeholder_view).error(R.drawable.placeholder_view).into(binding.imgSearchMovie)
        }
    }
}
