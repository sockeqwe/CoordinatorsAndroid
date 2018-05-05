package com.hannesdorfmann.navigation.coordinator

import android.support.v4.app.FragmentActivity
import com.hannesdorfmann.navigation.view.iap.IapNewsFragment
import com.hannesdorfmann.navigation.view.login.LoginFragment
import com.hannesdorfmann.navigation.view.newsdetails.NewsDetailFragment
import com.hannesdorfmann.navigation.view.newslist.NewsListFragment
import com.hannesdorfmann.navigation.view.onboarding.personalinteressts.PersonalInteresstsFragment
import com.hannesdorfmann.navigation.view.onboarding.welcome.WelcomeFragment

class Navigator {

    var activty: FragmentActivity? = null

    fun showOnboarindPersonalInteressts() {
        activty!!.supportFragmentManager
                .beginTransaction()
                .replace(com.hannesdorfmann.navigation.R.id.fragmentContainer, PersonalInteresstsFragment())
                .commit()

    }

    fun showOnboadingWelcome() {
        activty!!.supportFragmentManager
                .beginTransaction()
                .replace(com.hannesdorfmann.navigation.R.id.fragmentContainer, WelcomeFragment())
                .commit()

    }

    fun showInAppPurchases(addToBackStack: Boolean) {
        val t = activty!!.supportFragmentManager
                .beginTransaction()
                .replace(com.hannesdorfmann.navigation.R.id.fragmentContainer, IapNewsFragment())

        if (addToBackStack)
            t.addToBackStack(null)

        t.commit()
    }

    fun showLogin() {
        activty!!.supportFragmentManager
                .beginTransaction()
                .replace(com.hannesdorfmann.navigation.R.id.fragmentContainer, LoginFragment())
                .commit()

    }

    fun closeNews() {
        activty!!.supportFragmentManager
                .popBackStack()
    }

    fun showNewsList() {
        activty!!.supportFragmentManager
                .beginTransaction()
                .replace(com.hannesdorfmann.navigation.R.id.fragmentContainer, NewsListFragment())
                .commit()

    }

    fun showNewsDetails(newsId: Int) {
        activty!!.supportFragmentManager
                .beginTransaction()
                .replace(com.hannesdorfmann.navigation.R.id.fragmentContainer, NewsDetailFragment())
                .addToBackStack(null)
                .commit()
    }

}