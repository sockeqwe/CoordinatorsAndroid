package com.hannesdorfmann.navigation.coordinator

class OnboardingFlowCoordinator(
        private val navigator: Navigator,
        private val onboardingFinished: () -> Unit
) {


    fun onPersonalInterestsSelected() {
        onboardingFinished()
    }

    fun onWelcomeShown() {
        navigator.showOnboarindPersonalInteressts()
    }

    fun start() {
        navigator.showOnboadingWelcome()
    }

}