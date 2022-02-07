package com.example.stateapp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stateapp.data.Repository
import com.example.stateapp.model.Post
import javax.inject.Inject

class MainViewModel @Inject constructor(
    var repository: Repository
) : ViewModel() {

    private lateinit var posts: MutableLiveData<List<Post>>

    private fun getRemotePosts(): MutableLiveData<List<Post>> {
        posts.value = repository.getRemotePosts()
        return posts
    }

    private fun getLocalPosts(): MutableLiveData<List<Post>> {
        posts.value = repository.getLocalPosts()
        return posts
    }

    private fun insertPosts(posts: List<Post>) {
        repository.insertPosts(posts)
    }

    fun initData(): MutableLiveData<List<Post>> {
        posts = MutableLiveData()
        if (repository.getApiService() != null) {
            posts.value = getRemotePosts().value!!
            insertPosts(posts.value!!)
            return posts
        }
        posts.value = getLocalPosts().value!!
        return posts
    }
}