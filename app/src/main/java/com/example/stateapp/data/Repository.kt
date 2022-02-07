package com.example.stateapp.data

import com.example.stateapp.data.api.ApiService
import com.example.stateapp.data.database.dao.PostDao
import com.example.stateapp.model.Post
import kotlinx.coroutines.*
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService?,
    private val postDao: PostDao
) {
    fun getLocalPosts(): List<Post> {
        val deferredPost = CoroutineScope(Dispatchers.IO).async {
            postDao.getAllPosts()
        }
        var posts: List<Post>
        runBlocking {
            posts = deferredPost.await()
        }
        return posts
    }

    fun getRemotePosts(): List<Post> {
        if (apiService != null) {
            val deferredPost = CoroutineScope(Dispatchers.IO).async {
                apiService.getAllPosts().body()
            }
            var posts: List<Post>
            runBlocking {
                posts = deferredPost.await()!!
            }
            return posts
        }
        return emptyList()
    }

    fun insertPosts(posts: List<Post>) {
        CoroutineScope(Dispatchers.IO).launch {
            postDao.insertPosts(posts)
        }
    }

    fun getApiService(): ApiService? {
        return apiService
    }
}