package com.hannesdorfmann.navigation.coordinator

import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.widget.Toast
import com.hannesdorfmann.navigation.view.iap.IapNewsFragment
import com.hannesdorfmann.navigation.view.login.LoginFragment
import com.hannesdorfmann.navigation.view.newsdetails.NewsDetailFragment
import com.hannesdorfmann.navigation.view.newslist.NewsListFragment
import com.hannesdorfmann.navigation.view.onboarding.personalinterests.PersonalInterestsFragment
import com.hannesdorfmann.navigation.view.onboarding.welcome.WelcomeFragment

class Navigator {

    var activty: FragmentActivity? = null

    fun showOnboarindPersonalInterests() {
        activty!!.supportFragmentManager
                .beginTransaction()
                .replace(com.hannesdorfmann.navigation.R.id.fragmentContainer, PersonalInterestsFragment())
                .commit()

    }

    fun showOnboadingWelcome() {
        activty!!.supportFragmentManager
                .beginTransaction()
                .replace(com.hannesdorfmann.navigation.R.id.fragmentContainer, WelcomeFragment())
                .commit()

    }

    fun showInAppPurchases() {
        activty!!.supportFragmentManager
                .beginTransaction()
                .replace(com.hannesdorfmann.navigation.R.id.fragmentContainer, IapNewsFragment())
                .addToBackStack(null)
                .commit()
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
                .replace(com.hannesdorfmann.navigation.R.id.fragmentContainer, NewsDetailFragment.newInstance(newsId))
                .addToBackStack("NewsDetail")
                .commit()
    }

    fun closeIap() {
        activty!!.supportFragmentManager
                .popBackStack("NewsDetail", FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    fun showRegistration() {
        Toast.makeText(activty!!, "Not implemented, it's just a demo", Toast.LENGTH_SHORT).show()
    }

    fun showRecoverPassword() {
        Toast.makeText(activty!!, "Not implemented, it's just a demo\n\nUse username=Hannes password=123", Toast.LENGTH_SHORT).show()
    }

}