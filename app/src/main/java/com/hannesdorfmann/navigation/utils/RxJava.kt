package com.hannesdorfmann.navigation.utils

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

operator fun CompositeDisposable.plus(d: Disposable) : CompositeDisposable {
    this.add(d)
    return this
}