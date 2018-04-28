package com.hannesdorfmann.navigation.flowold.onboarding

import com.hannesdorfmann.navigation.domain.ab.AbTest

class OnboardingFlowCoordinator(
        private val abTest: AbTest,
        private val flowCompleted: () -> Unit
) {

    private lateinit var flow: OnboardingFlow


    fun onPersonalInterestsSelected() {
        next()
    }

    fun onSubscriptionClosed() {
        next()
    }

    /**
     * Transition to the next state
     */
    private fun next() {
        val currentState = flow.state
        when (currentState) {
            OnboardingFlowState.PERSONAL_INTERESTS ->
                if (abTest.isA()) {
                    TODO("Show Subscription")
                } else {
                    flowCompleted()
                }
            OnboardingFlowState.ASK_SUBSCRIPTION -> flowCompleted()
        }
    }

}