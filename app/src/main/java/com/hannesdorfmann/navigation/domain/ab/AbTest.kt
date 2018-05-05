package com.hannesdorfmann.navigation.domain.ab

class AbTest {
    private var isA = false
    fun isA(): Boolean = isA
    fun isB(): Boolean = !isA

    fun toggleAssignedTestGroup() {
        isA = !isA
    }
}