package com.hannesdorfmann.navigation.utils

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer

inline fun <T> LiveData<T>.subscribe(owner: LifecycleOwner, crossinline subscriber: (T) -> Unit) {
    observe(owner, object : Observer<T> {
        override fun onChanged(value: T?) {
            subscriber(value!!)
        }
    })
}