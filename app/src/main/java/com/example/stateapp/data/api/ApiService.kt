package com.example.stateapp.data.api

import com.example.stateapp.model.Post
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/posts")
    suspend fun getAllPosts(): Response<List<Post>>
}