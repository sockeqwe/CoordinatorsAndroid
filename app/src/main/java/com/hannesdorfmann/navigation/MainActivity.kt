package com.hannesdorfmann.navigation

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.isOnBackstack
import android.support.v7.app.AppCompatActivity
import com.hannesdorfmann.navigation.utils.navigator
import com.hannesdorfmann.navigation.utils.rootFlowCoordinator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigator.activty = this
    }


    override fun onDestroy() {
        super.onDestroy()
        navigator.activty = null
    }


    override fun onBackPressed() {

        val handled = recursivelyDispatchOnBackPressed(supportFragmentManager)
        if (!handled) {
            super.onBackPressed()
        }
    }

    private fun recursivelyDispatchOnBackPressed(fm: FragmentManager): Boolean {
        if (fm.backStackEntryCount == 0)
            return false

        val reverseOrder = fm.fragments.filter { it is OnBackPressed && it.isOnBackstack() }.reversed()
        for (f in reverseOrder) {
            val handledByChildFragments = recursivelyDispatchOnBackPressed(f.childFragmentManager)
            if (handledByChildFragments) {
                return true
            }

            val backpressable = f as OnBackPressed
            if (backpressable.onBackPressed()) {
                return true
            }
        }
        return false
    }
}
