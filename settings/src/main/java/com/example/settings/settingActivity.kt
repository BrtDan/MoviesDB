package com.example.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.settings.databinding.SettingactivityLayoutBinding

class settingActivity : AppCompatActivity() {
    private val binding by lazy { SettingactivityLayoutBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.list.setOnClickListener{
            finish()
        }

    }

}