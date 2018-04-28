package com.hannesdorfmann.navigation.flowold.onboarding

import com.hannesdorfmann.navigation.flowold.Flow
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OnboardingFlow(
        val state: OnboardingFlowState
) : Flow

enum class OnboardingFlowState {
    PERSONAL_INTERESTS,
    ASK_SUBSCRIPTION
}
