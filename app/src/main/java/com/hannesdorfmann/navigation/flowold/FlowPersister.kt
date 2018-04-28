package com.hannesdorfmann.navigation.flowold

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.util.ArrayMap
import kotlinx.android.parcel.Parcelize

class FlowPersister(
        private val flowCoordinatorFactory: FlowCoordinatorFactory
) {
    private companion object {
        private val KEY_ROOT_FLOW_COORDINATOR_DATA = "RootFlowCoordinatorData"
    }

    fun save(b: Bundle, rootFlowCoordinator: FlowCoordinator) {
        val rootCoordinatorData = FlowCoordinatorData(
                key = rootFlowCoordinator.key,
                flow = rootFlowCoordinator.flow,
                parentFlowCoordinatorKey = null,
                childCoordinatorData = buildChildTree(rootFlowCoordinator)
        )

        b.putParcelable(KEY_ROOT_FLOW_COORDINATOR_DATA, rootCoordinatorData)
    }

    /**
     * Recursively build [FlowCoordinatorData] for each child
     */
    private fun buildChildTree(parent: FlowCoordinator): ArrayList<FlowCoordinatorData> {

        val data = ArrayList<FlowCoordinatorData>(parent.childFlowCoordinators.size)

        for (child in parent.childFlowCoordinators) {
            data.add(
                    FlowCoordinatorData(
                            key = child.key,
                            flow = child.flow,
                            parentFlowCoordinatorKey = parent.key,
                            childCoordinatorData = buildChildTree(child)
                    )
            )
        }

        return data
    }

    /**
     * Restores the root [FlowCoordinator] containing all child [FlowCoordinator]
     */
    fun restore(b: Bundle): FlowCoordinator {
        val data: FlowCoordinatorData = b.getParcelable(KEY_ROOT_FLOW_COORDINATOR_DATA)
        val flowCoordinators = ArrayMap<String, FlowCoordinator>()

        val root = flowCoordinatorFactory.createFlowCoordinator(data.key, null)
        root.flow = data.flow
        flowCoordinators[data.key] = root
        root.childFlowCoordinators = restoreChildren(childrenData = data.childCoordinatorData, parent = root)

        return root
    }


    /**
     * Restores the children
     */
    private fun restoreChildren(childrenData: List<FlowCoordinatorData>, parent: FlowCoordinator): MutableList<FlowCoordinator> {
        val childCoordinators = ArrayList<FlowCoordinator>(childrenData.size)
        for (childData in childrenData) {
            val childCoordinator = flowCoordinatorFactory.createFlowCoordinator(childData.key, parent)
            childCoordinator.flow = childData.flow
            childCoordinator.childFlowCoordinators = restoreChildren(
                    childrenData = childData.childCoordinatorData,
                    parent = childCoordinator
            )
            childCoordinators.add(childCoordinator)
        }
        return childCoordinators
    }


    @Parcelize
    private data class FlowCoordinatorData(
            val key: String,
            val parentFlowCoordinatorKey: String?,
            val flow: Flow,
            val childCoordinatorData: ArrayList<FlowCoordinatorData>
    ) : Parcelable
}