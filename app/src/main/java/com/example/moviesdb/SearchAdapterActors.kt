package com.example.moviesdb

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesdb.databinding.SearchItemActorsBinding
import com.example.network.ActorsSearch
import com.squareup.picasso.Picasso

class SearchAdapterActors: ListAdapter<ActorsSearch, SearchAdapterActors.SearchActorsViewHolder>(
    DIFF_CALLBACK
) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ActorsSearch>() {
            override fun areItemsTheSame(oldItem: ActorsSearch, newItem: ActorsSearch): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ActorsSearch, newItem: ActorsSearch): Boolean {
                return areItemsTheSame(oldItem, newItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchActorsViewHolder {
        return SearchActorsViewHolder(
            SearchItemActorsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchActorsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SearchActorsViewHolder(val binding: SearchItemActorsBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(resultActors: ActorsSearch) {
            binding.nameActors.text = resultActors.name
            val lang : String? = resultActors.known_for?.get(0)?.original_language
            val date : String? = resultActors.known_for?.get(0)?.releaseDate
            val overview : String? = resultActors.known_for?.get(0)?.overview
            val title : String? = resultActors.known_for?.get(0)?.nameTitle
            val vote : Float? = resultActors.known_for?.get(0)?.vote_average
            val formattedVoteAvg = String.format("%.1f", vote ?: 0.0f)
            binding.knownForActors.text = "\nKnown for:\n\n$title $date ($lang) | $formattedVoteAvg/10\n\n$overview"
            val baseUrl = "https://image.tmdb.org/t/p/w500"
            val posterPath = resultActors.profile_path
            var imageUrl = "$baseUrl$posterPath"
            Picasso.get().load(imageUrl).placeholder(R.drawable.placeholder_view).error(R.drawable.placeholder_view).into(binding.imgSearchActors)
        }
    }
}