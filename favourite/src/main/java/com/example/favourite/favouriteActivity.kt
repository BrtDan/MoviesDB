package com.example.favourite

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.favourite.databinding.FavouriteactivityLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class favouriteActivity : AppCompatActivity() {

    private val binding by lazy { FavouriteactivityLayoutBinding.inflate(layoutInflater) }
    val viewModel by viewModels<FavouriteViewModel>()
    private val adapter by lazy {
        ProdAdapter(

            onClickDel = {
                Toast.makeText(this, "${it.name} eliminato dai preferiti", Toast.LENGTH_LONG)
                    .show()
                viewModel.deleteFromDB(it.idProduct.toString().toInt())
            }
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.recycleView.adapter = adapter
        binding.recycleView.layoutManager = LinearLayoutManager(this)

        viewModel.getDataFromDb()
        viewModel.text.observe(this) {
            val list = it.data
            adapter.submitList(list)
            if(it.data.isNullOrEmpty()){
                binding.textEmpty.visibility = View.VISIBLE
            } else {
                binding.textEmpty.visibility = View.GONE
            }
        }

        binding.list.setOnClickListener{
            finish()
        }

    }
}