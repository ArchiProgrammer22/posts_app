package com.example.stateapp.presentation.screens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stateapp.App
import com.example.stateapp.R
import com.example.stateapp.model.Post
import com.example.stateapp.presentation.adapter.RecyclerViewAdapter
import com.example.stateapp.presentation.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnRecyclerClickListener {


    @Inject
    lateinit var viewModel: MainViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var posts: List<Post>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (applicationContext as App).appComponent.inject(this)

        posts = viewModel.initData().value!!

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.initData().observe(this, Observer {
            posts = it
            recyclerView.adapter = RecyclerViewAdapter(posts, this)
        })
    }

    override fun onClick(position: Int) {
        val intent = Intent(this, FullPostInfoActivity::class.java)
        intent.putExtra(
            resources.getString(R.string.title_tag),
            posts[position].title
        )
        intent.putExtra(
            resources.getString(R.string.body_tag),
            posts[position].body
        )
        startActivity(intent)
    }
}