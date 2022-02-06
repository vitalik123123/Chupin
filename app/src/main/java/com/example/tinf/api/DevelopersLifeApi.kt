package com.example.tinf.api

import com.example.tinf.domain.Post
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface DevelopersLifeApi {

    @GET("random?json=true")
    suspend fun getPost(): Post

    companion object {
        const val BASE_URL = "https://developerslife.ru"

        fun create(): DevelopersLifeApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create()
        }
    }
}
