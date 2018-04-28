package com.hannesdorfmann.navigation

import android.app.Activity
import android.os.Bundle
import com.hannesdorfmann.navigation.utils.navigator
import kotlinx.android.synthetic.main.fragment_purchase.*

class MyActivity : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        purchase.setOnClickListener{
            navigator.showNewsDetails(123)
        }
    }
}