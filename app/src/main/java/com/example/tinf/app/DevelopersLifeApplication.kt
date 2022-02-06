package com.example.tinf.app

import android.app.Application
import com.example.tinf.di.Injector

class DevelopersLifeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Injector.init(this)
    }
}
