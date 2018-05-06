package com.hannesdorfmann.navigation.coordinator

class LoginFlowCoordinator(
        private val navigator: Navigator
) {

    fun start(){
        navigator.showLogin()
    }

    fun registerNewUser(){
        navigator.showRegistration()
    }

    fun forgotPassword(){
        navigator.showRecoverPassword()
    }
}