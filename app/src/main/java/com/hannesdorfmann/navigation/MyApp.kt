package com.hannesdorfmann.navigation

import android.app.Application

class MyApp : Application() {

    val viewModelFactory by lazy {
        AppViewModelFactory(this)
    }

    override fun onCreate() {
        super.onCreate()
        // println(viewModelFactory.navigator) // init
    }
}