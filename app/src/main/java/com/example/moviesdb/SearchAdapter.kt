package com.example.moviesdb

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesdb.databinding.SearchItemBinding
import com.example.network.ResultSearch
import com.example.network.WrapperMovie
import com.squareup.picasso.Picasso

class SearchAdapter(
    private val onClickStar: (movie: ResultSearch) -> Unit,
    private val checkIfIsFavourite: (movie: ResultSearch) -> Unit,
    private val onClickDel: (movie: ResultSearch) -> Unit
): ListAdapter<WrapperMovie, SearchAdapter.SearchViewHolder>(
    DIFF_CALLBACK
) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WrapperMovie>() {
            override fun areItemsTheSame(oldItem: WrapperMovie, newItem: WrapperMovie): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: WrapperMovie, newItem: WrapperMovie): Boolean {
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
        fun bind(Res: WrapperMovie) {

            if(Res.isFavourite){
                binding.favourite.setImageResource(R.drawable.baseline_star_24)
            } else {
                binding.favourite.setImageResource(R.drawable.baseline_star_border_24)
            }

            binding.titleSearch.text = Res.search.title
            val date : String? = Res.search.release_date
            val lang : String? = Res.search.original_language
            val overview : String? = Res.search.overview
            val vote : Float? = Res.search.vote_average
            val formattedVoteAvg = String.format("%.1f", vote ?: 0.0f)
            binding.dataLangOverviewVote.text = "$date ($lang) | $formattedVoteAvg/10\n\n$overview"
            val baseUrl = "https://image.tmdb.org/t/p/w500"
            val posterPath = Res.search.poster_path
            var imageUrl = "$baseUrl$posterPath"
            Picasso.get().load(imageUrl).placeholder(R.drawable.placeholder_view).error(R.drawable.placeholder_view).into(binding.imgSearchMovie)

            binding.favourite.setOnClickListener {
                println(Res.isFavourite)
                if (Res.isFavourite){
                    onClickDel(Res.search)
                    binding.favourite.setImageResource(R.drawable.baseline_star_border_24)
                } else{
                    onClickStar(Res.search)
                    binding.favourite.setImageResource(R.drawable.baseline_star_24)
                }
            }
        }
    }
}