package com.jetpackcomposedemo.ui.mvvm.network

import com.jetpackcomposedemo.ui.mvvm.model.Post
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): List<Post>


    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }
}