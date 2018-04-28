package com.hannesdorfmann.navigation.view.iap

import android.arch.lifecycle.ViewModel

class IapViewModel(
        private var close: (() -> Boolean)?
) : ViewModel() {

    fun onBuyingClicked() {
        close!!()
    }

    fun onBackPressed() = close!!()


    override fun onCleared() {
        super.onCleared()
        close = null
    }
}