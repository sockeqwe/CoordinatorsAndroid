package com.hannesdorfmann.navigation.flowold

interface FlowCoordinator {

    /**
     * A key that uniquly identifies this FlowCoordinator
     */
    val key : String

    /**
     * Flow
     */
    var flow: Flow

    /**
     * This method is called to start the coordinator.
     * This basically means transition to the coordinators initial state.
     */
    fun start()

    /**
     * List of child FlowCoordinators
     */
    var childFlowCoordinators : MutableList<FlowCoordinator>
}