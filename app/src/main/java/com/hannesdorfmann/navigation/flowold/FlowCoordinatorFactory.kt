package com.hannesdorfmann.navigation.flowold

interface FlowCoordinatorFactory {

    fun createFlowCoordinator(key : String, parent : FlowCoordinator?) : FlowCoordinator
}