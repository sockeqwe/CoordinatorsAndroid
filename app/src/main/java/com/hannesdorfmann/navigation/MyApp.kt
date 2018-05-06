package com.hannesdorfmann.navigation

import android.app.Application

class MyApp : Application() {

    val viewModelFactory by lazy {
        AppViewModelFactory(this)
    }
}