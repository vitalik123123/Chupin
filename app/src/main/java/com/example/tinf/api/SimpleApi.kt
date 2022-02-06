package com.example.tinf.api

import com.example.tinf.model.Post
import retrofit2.http.GET

interface SimpleApi {

    @GET("random?json=true")
    suspend fun getPost(): Post

}