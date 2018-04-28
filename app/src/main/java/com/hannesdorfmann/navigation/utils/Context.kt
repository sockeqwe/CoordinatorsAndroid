package com.hannesdorfmann.navigation.utils

import android.app.Activity
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import com.hannesdorfmann.navigation.AppViewModelFactory
import com.hannesdorfmann.navigation.MyApp
import com.hannesdorfmann.navigation.coordinator.Navigator
import com.hannesdorfmann.navigation.coordinator.RootFlowCoordinator

val Fragment.application
    get() = requireActivity().application as MyApp

val Fragment.viewModelFactory: ViewModelProvider.Factory
    get() = application.viewModelFactory


val Activity.navigator: Navigator
    get() = (application as MyApp).viewModelFactory.navigator

val Activity.rootFlowCoordinator: RootFlowCoordinator
    get() = (application as MyApp).viewModelFactory.rootCoordinator



inline fun <reified VM : ViewModel> Fragment.getViewModel(): VM {
    return ViewModelProviders.of(this, application.viewModelFactory!!)[VM::class.java]
}