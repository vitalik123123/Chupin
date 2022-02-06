package com.example.tinf.di

import android.app.Application
import com.example.tinf.ConnectionLiveData
import com.example.tinf.MainViewModel
import com.example.tinf.api.DevelopersLifeApi
import com.example.tinf.domain.PostsRepository
import kotlin.LazyThreadSafetyMode.NONE

class AppComponent(private val application: Application) {

    private val repository = PostsRepository.Impl(DevelopersLifeApi.create())

    val mainViewModel = MainViewModel(repository)

    val connectivityLiveData by lazy(NONE) {
        ConnectionLiveData(application)
    }
}
