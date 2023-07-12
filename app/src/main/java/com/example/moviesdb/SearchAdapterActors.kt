package com.example.moviesdb

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesdb.databinding.SearchItemActorsBinding
import com.example.network.ActorsSearch
import com.example.network.WrapperActors
import com.squareup.picasso.Picasso

class SearchAdapterActors(
    private val onClickStar: (actors: ActorsSearch) -> Unit,
    private val checkIfIsFavourite: (actors: ActorsSearch) -> Unit,
    private val onClickDel: (actors: ActorsSearch) -> Unit
): ListAdapter<WrapperActors, SearchAdapterActors.SearchActorsViewHolder>(
    DIFF_CALLBACK
) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WrapperActors>() {
            override fun areItemsTheSame(oldItem: WrapperActors, newItem: WrapperActors): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: WrapperActors, newItem: WrapperActors): Boolean {
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

    inner class SearchActorsViewHolder(val binding: SearchItemActorsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(Res: WrapperActors) {

            if(Res.isFavourite){
                binding.favourite.setImageResource(R.drawable.baseline_star_24)
            } else {
                binding.favourite.setImageResource(R.drawable.baseline_star_border_24)
            }

            binding.nameActors.text = Res.searchActors.name
            val lang : String? = Res.searchActors.known_for?.get(0)?.original_language
            val date : String? = Res.searchActors.known_for?.get(0)?.releaseDate
            val overview : String? = Res.searchActors.known_for?.get(0)?.overview
            val title : String? = Res.searchActors.known_for?.get(0)?.nameTitle
            val vote : Float? = Res.searchActors.known_for?.get(0)?.vote_average
            val formattedVoteAvg = String.format("%.1f", vote ?: 0.0f)
            binding.knownForActors.text = "\nKnown for:\n\n$title $date ($lang) | $formattedVoteAvg/10\n\n$overview"
            val baseUrl = "https://image.tmdb.org/t/p/w500"
            val posterPath = Res.searchActors.profile_path
            var imageUrl = "$baseUrl$posterPath"
            Picasso.get().load(imageUrl).placeholder(R.drawable.placeholder_view).error(R.drawable.placeholder_view).into(binding.imgSearchActors)

            binding.favourite.setOnClickListener {
                println(Res.isFavourite)
                if (Res.isFavourite){
                    onClickDel(Res.searchActors)
                    binding.favourite.setImageResource(R.drawable.baseline_star_border_24)
                } else{
                    onClickStar(Res.searchActors)
                    binding.favourite.setImageResource(R.drawable.baseline_star_24)
                }
            }
        }
    }
}


