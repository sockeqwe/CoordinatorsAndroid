package com.hannesdorfmann.navigation.coordinator

class LoginFlowCoordinator(
        private val navigator: Navigator
) {

    fun start(){
        navigator.showLogin()
    }
}