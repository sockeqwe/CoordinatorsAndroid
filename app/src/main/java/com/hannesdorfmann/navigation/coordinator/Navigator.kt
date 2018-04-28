package com.hannesdorfmann.navigation.coordinator

import android.support.v4.app.FragmentActivity
import com.hannesdorfmann.navigation.view.iap.IapFragment
import com.hannesdorfmann.navigation.view.login.LoginFragment
import com.hannesdorfmann.navigation.view.newsdetails.NewsDetailFragment
import com.hannesdorfmann.navigation.view.newslist.NewsListFragment
import com.hannesdorfmann.navigation.view.onboarding.PersonalInteresstsFragment

class Navigator {

    var activty: FragmentActivity? = null

    fun showOnboarding() {
        activty!!.supportFragmentManager
                .beginTransaction()
                .replace(com.hannesdorfmann.navigation.R.id.root, PersonalInteresstsFragment())
                .commit()

    }

    fun showInAppPurchases() {
        activty!!.supportFragmentManager
                .beginTransaction()
                .replace(com.hannesdorfmann.navigation.R.id.root, IapFragment())
                .commit()

    }

    fun showLogin() {
        activty!!.supportFragmentManager
                .beginTransaction()
                .replace(com.hannesdorfmann.navigation.R.id.root, LoginFragment())
                .commit()

    }

    fun closeNews() {
        activty!!.supportFragmentManager
                .popBackStack()
    }

    fun showNewsList() {
        activty!!.supportFragmentManager
                .beginTransaction()
                .replace(com.hannesdorfmann.navigation.R.id.root, NewsListFragment())
                .commit()

    }

    fun showNewsDetails(newsId: Int) {
        activty!!.supportFragmentManager
                .beginTransaction()
                .replace(com.hannesdorfmann.navigation.R.id.root, NewsDetailFragment())
                .addToBackStack(null)
                .commit()
    }

}