package com.example.favourite

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.favourite.databinding.FavouriteactivityLayoutBinding
import com.example.network.MoviesDB
import com.example.network.MoviesDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.reflect.typeOf

@AndroidEntryPoint
class favouriteActivity : AppCompatActivity() {

    private val binding by lazy { FavouriteactivityLayoutBinding.inflate(layoutInflater) }
    val viewModel by viewModels<FavouriteViewModel>()
    private val adapter by lazy { ProdAdapter }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


//        binding.recycleView.adapter = adapter
        binding.recycleView.layoutManager = LinearLayoutManager(this)

        viewModel.getDataFromDb()
        viewModel.text.observe(this) {
            val list = it.data
            println()
//            adapter.submitList(list)
        }

        binding.list.setOnClickListener{
            finish()
        }

    }
}