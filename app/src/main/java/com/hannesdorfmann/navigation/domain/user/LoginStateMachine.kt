package com.hannesdorfmann.navigation.domain.user

import com.jakewharton.rxrelay2.PublishRelay

class LoginStateMachine(private val usermanager: Usermanager) {

    data class LoginCredentials(val username: String, val password: String)

    val input = PublishRelay.create<LoginCredentials>()

    val state = input.switchMap { (username, password) ->
        usermanager.signIn(username, password)
                .map {
                    when (it) {
                        Usermanager.SigninResult.SUCCESSFUL -> State.Successful
                        Usermanager.SigninResult.ERROR -> State.UnknownUserError
                    }
                }
                .startWith(State.Loading)
    }


    sealed class State {
        object Loading : State()
        object UnknownUserError : State()
        object Successful : State()
    }
}