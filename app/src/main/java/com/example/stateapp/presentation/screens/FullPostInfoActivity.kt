package com.example.stateapp.presentation.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.stateapp.R
import com.example.stateapp.databinding.ActivityFullPostInfoBinding

class FullPostInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityFullPostInfoBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_full_post_info)

        binding.title = intent.getStringExtra(
            resources.getString(R.string.title_tag)
        )
        binding.body = intent.getStringExtra(
            resources.getString(R.string.body_tag)
        )
    }
}