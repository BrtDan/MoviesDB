package com.example.moviesdb

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesdb.databinding.SearchItemBinding
import com.example.network.Result
import com.example.network.ResultSearch
import com.squareup.picasso.Picasso

class SearchAdapter: ListAdapter<ResultSearch, SearchAdapter.SearchViewHolder>(
    DIFF_CALLBACK
) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ResultSearch>() {
            override fun areItemsTheSame(oldItem: ResultSearch, newItem: ResultSearch): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ResultSearch, newItem: ResultSearch): Boolean {
                return areItemsTheSame(oldItem, newItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SearchViewHolder(val binding: SearchItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(Res: ResultSearch) {
            binding.titleSearch.text = Res.title
            val date : String? = Res.release_date
            val lang : String? = Res.original_language
            val overview : String? = Res.overview
            val vote : Float? = Res.vote_average
            val formattedVoteAvg = String.format("%.1f", vote ?: 0.0f)
            binding.dataLangOverviewVote.text = "$date ($lang) | $formattedVoteAvg/10\n\n$overview"
            val baseUrl = "https://image.tmdb.org/t/p/w500"
            val posterPath = Res.poster_path
            var imageUrl = "$baseUrl$posterPath"
            Picasso.get().load(imageUrl).into(binding.imgSearchMovie)
        }
    }
}