package com.example.tinf.repository

import com.example.tinf.api.RetrofitInstance
import com.example.tinf.model.Post

class Repository {

    suspend fun getPost(): Post{
        return RetrofitInstance.api.getPost()
    }
}