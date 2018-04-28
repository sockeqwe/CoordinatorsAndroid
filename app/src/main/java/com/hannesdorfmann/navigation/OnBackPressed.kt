package com.hannesdorfmann.navigation

interface OnBackPressed {

    /**
     * Returns true, if the on back pressed event has been handled by this Fragment.
     * Otherwise return false
     */
    fun onBackPressed() : Boolean
}