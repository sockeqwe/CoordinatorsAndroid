package com.hannesdorfmann.navigation.flowold

class Navigator(private val flowFactory : FlowCoordinatorFactory ) {


    lateinit var currentFlowCoordinator: FlowCoordinator


    fun onFlowCoordinatorCompleted(){
        TODO("Move to the next coordinator")
    }

}