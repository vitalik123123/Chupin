package com.example.tinf.di

import android.app.Application

object Injector {

    lateinit var appComponent: AppComponent

    fun init(application: Application) {
        appComponent = AppComponent(application)
    }
}