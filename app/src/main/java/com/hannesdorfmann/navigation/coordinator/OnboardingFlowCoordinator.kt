package com.hannesdorfmann.navigation.coordinator

import com.hannesdorfmann.navigation.domain.ab.AbTest

class OnboardingFlowCoordinator(
        private val navigator: Navigator,
        private val abTest: AbTest,
        private val onboardingFinished : () -> Unit
) {


    fun onInAppPurchaseClosed(){
        onboardingFinished()
    }

    fun onPersonalInterestsSelected(){
        if (abTest.isA()){
            onboardingFinished()
        } else {
            navigator.showInAppPurchases(addToBackStack = false)
        }
    }


    fun start() {
        navigator.showOnboarding()
    }

}