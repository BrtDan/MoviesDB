package com.example.moviesdb

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesdb.databinding.SearchItemBinding
import com.example.network.ResultSearch
import com.squareup.picasso.Picasso

class SearchAdapter(
    private val onClickStar: (movie: ResultSearch) -> Unit,
    private val checkIfIsFavourite: (movie: ResultSearch) -> Int
): ListAdapter<ResultSearch, SearchAdapter.SearchViewHolder>(
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

    inner class SearchViewHolder(val binding: SearchItemBinding): RecyclerView.ViewHolder(binding.root){
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
            Picasso.get().load(imageUrl).placeholder(R.drawable.placeholder_view).error(R.drawable.placeholder_view).into(binding.imgSearchMovie)

            binding.favourite.setOnClickListener {
                val isFavourite = checkIfIsFavourite(Res)
                println(isFavourite)
                onClickStar(Res)
            }
        }
    }
}


