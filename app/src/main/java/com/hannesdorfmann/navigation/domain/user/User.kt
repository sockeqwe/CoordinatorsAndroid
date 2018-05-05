package com.hannesdorfmann.navigation.domain.user

sealed class User

object NotAuthenticated : User()

data class AuthenticatedUser(val username: String, val onboardingCompleted: Boolean) : User()