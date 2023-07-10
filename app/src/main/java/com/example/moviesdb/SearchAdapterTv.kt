package com.example.moviesdb


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesdb.databinding.SearchItemTvBinding
import com.example.network.ResultTvSearch
import com.squareup.picasso.Picasso

class SearchAdapterTv: ListAdapter<ResultTvSearch, SearchAdapterTv.SearchTvViewHolder>(
    DIFF_CALLBACK
) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ResultTvSearch>() {
            override fun areItemsTheSame(oldItem: ResultTvSearch, newItem: ResultTvSearch): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ResultTvSearch, newItem: ResultTvSearch): Boolean {
                return areItemsTheSame(oldItem, newItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchTvViewHolder {
        return SearchTvViewHolder(
            SearchItemTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchTvViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SearchTvViewHolder(val binding: SearchItemTvBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(resultTvSearch: ResultTvSearch) {
            binding.titleSearchTv.text = resultTvSearch.name
            val date : String? = resultTvSearch.first_air_date
            val lang : String? = resultTvSearch.original_language
            val overview : String? = resultTvSearch.overview
            val vote : Float? = resultTvSearch.vote_average
            val formattedVoteAvg = String.format("%.1f", vote ?: 0.0f)
            binding.dataLangOverviewVoteTv.text = "$date ($lang) | $formattedVoteAvg/10\n\n$overview"
            val baseUrl = "https://image.tmdb.org/t/p/w500"
            val posterPath = resultTvSearch.poster_path
            var imageUrl = "$baseUrl$posterPath"
            Picasso.get().load(imageUrl).placeholder(R.drawable.placeholder_view).error(R.drawable.placeholder_view).into(binding.imgSearchTvSeries)
        }
    }
}