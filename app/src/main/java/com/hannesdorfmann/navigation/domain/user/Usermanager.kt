package com.hannesdorfmann.navigation.domain.user

import android.content.SharedPreferences
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Completable
import io.reactivex.Observable
import java.util.*

class Usermanager(private val sharedPreferences: SharedPreferences) {
    private val KEY_CURRENT_USER = "currentUser"
    private val KEY_ONBOARDING_COMPLETED = "onboardingCompleted"
    private val usernamePasswordMap = HashMap<String, String>()
    private val userRelay = BehaviorRelay.create<User>()
    val currentUser: Observable<User> = userRelay


    init {
        usernamePasswordMap["Hannes"] = "123"
        val current = sharedPreferences.getString(KEY_CURRENT_USER, null)
        userRelay.accept(
                if (current != null)
                    AuthenticatedUser(current, sharedPreferences.getBoolean(KEY_ONBOARDING_COMPLETED, false))
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
            userRelay.accept(AuthenticatedUser(username, false))
            SigninResult.SUCCESSFUL
        } else SigninResult.ERROR
    }


    fun logout() = Completable.fromCallable {
        sharedPreferences.edit().clear().commit()
        userRelay.accept(NotAuthenticated)
    }


    fun markOnboardingComplete(){
        sharedPreferences.edit().putBoolean(KEY_ONBOARDING_COMPLETED, true).apply()
    }


    enum class SigninResult {
        SUCCESSFUL, ERROR
    }

}