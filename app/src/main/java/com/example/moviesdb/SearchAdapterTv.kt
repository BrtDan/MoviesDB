package com.example.moviesdb


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesdb.databinding.SearchItemTvBinding
import com.example.network.ResultTvSearch
import com.example.network.WrapperTv
import com.squareup.picasso.Picasso

class SearchAdapterTv(
    private val onClickStar: (movie: ResultTvSearch) -> Unit,
    private val checkIfIsFavourite: (movie: ResultTvSearch) -> Unit,
    private val onClickDel: (movie: ResultTvSearch) -> Unit
): ListAdapter<WrapperTv, SearchAdapterTv.SearchTvViewHolder>(
    DIFF_CALLBACK
) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WrapperTv>() {
            override fun areItemsTheSame(oldItem: WrapperTv, newItem: WrapperTv): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: WrapperTv, newItem: WrapperTv): Boolean {
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

    inner class SearchTvViewHolder(val binding: SearchItemTvBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(Res: WrapperTv) {

            if(Res.isFavourite){
                binding.favourite.setImageResource(R.drawable.baseline_star_24)
            } else {
                binding.favourite.setImageResource(R.drawable.baseline_star_border_24)
            }

            binding.titleSearchTv.text = Res.searchTv.name
            val date : String? = Res.searchTv.first_air_date
            val lang : String? = Res.searchTv.original_language
            val overview : String? = Res.searchTv.overview
            val vote : Float? = Res.searchTv.vote_average
            val formattedVoteAvg = String.format("%.1f", vote ?: 0.0f)
            binding.dataLangOverviewVoteTv.text = "$date ($lang) | $formattedVoteAvg/10\n\n$overview"
            val baseUrl = "https://image.tmdb.org/t/p/w500"
            val posterPath = Res.searchTv.poster_path
            var imageUrl = "$baseUrl$posterPath"
            Picasso.get().load(imageUrl).placeholder(R.drawable.placeholder_view).error(R.drawable.placeholder_view).into(binding.imgSearchTvSeries)
            binding.favourite.setOnClickListener {
                println(Res.isFavourite)
                if (Res.isFavourite){
                    onClickDel(Res.searchTv)
                    binding.favourite.setImageResource(R.drawable.baseline_star_border_24)
                } else{
                    onClickStar(Res.searchTv)
                    binding.favourite.setImageResource(R.drawable.baseline_star_24)
                }
            }
        }
    }
}