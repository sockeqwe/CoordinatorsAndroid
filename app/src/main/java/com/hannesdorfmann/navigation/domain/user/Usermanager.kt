package com.hannesdorfmann.navigation.domain.user

import android.content.SharedPreferences
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import java.util.*

class Usermanager(private val sharedPreferences: SharedPreferences) {
    private val KEY_CURRENT_USER = "currentUser"
    private val usernamePasswordMap = HashMap<String, String>()
    private val userRelay = BehaviorRelay.create<User>()
    val currentUser: Observable<User> = userRelay


    init {
        usernamePasswordMap["hannes"] = "123"
        val current = sharedPreferences.getString(KEY_CURRENT_USER, null)
        userRelay.accept(
                if (current != null)
                    AuthenticatedUser(current)
                else
                    NotAuthenticated
        )
    }


    fun signIn(username: String, password: String) = Observable.fromCallable {
        Thread.sleep(1000)
        val foundPassword = usernamePasswordMap[username]
        if (foundPassword == null)
            SigninResult.ERROR
        else if (foundPassword == password) {
            sharedPreferences.edit().putString(KEY_CURRENT_USER, username).commit()
            userRelay.accept(AuthenticatedUser(username))
            SigninResult.SUCCESSFUL
        } else SigninResult.ERROR
    }


    fun logout() = Completable.fromCallable {
        sharedPreferences.edit().clear().commit()
        userRelay.accept(NotAuthenticated)
    }

    enum class SigninResult {
        SUCCESSFUL, ERROR
    }

}