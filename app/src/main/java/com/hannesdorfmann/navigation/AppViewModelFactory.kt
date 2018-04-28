package com.hannesdorfmann.navigation

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.util.Log
import com.hannesdorfmann.navigation.coordinator.*
import com.hannesdorfmann.navigation.domain.ab.AbTest
import com.hannesdorfmann.navigation.domain.news.NewsRepository
import com.hannesdorfmann.navigation.domain.user.LoginStateMachine
import com.hannesdorfmann.navigation.domain.user.Usermanager
import com.hannesdorfmann.navigation.view.iap.IapViewModel
import com.hannesdorfmann.navigation.view.login.LoginViewModel
import com.hannesdorfmann.navigation.view.newsdetails.NewsDetailViewModel
import com.hannesdorfmann.navigation.view.newslist.NewsListViewModel
import com.hannesdorfmann.navigation.view.onboarding.PersonalInteresstsViewModel


class AppViewModelFactory(application: Application) : ViewModelProvider.Factory {


    //
    // Don't do this add home!
    //
    // It's just because I'm super lazy to set up dagger properly and just want to hack together a
    // quick prototype and dependency injection is not the main focus of this prototype.
    //

    private val usermanager = Usermanager(application.getSharedPreferences("UserManager", Context.MODE_PRIVATE))
    private val newsRepository = NewsRepository()

    private val abTest = AbTest()
    val navigator = Navigator()


    //
    // Coordinator
    //
    val rootCoordinator = RootFlowCoordinator(usermanager, navigator)
    private val onboardingCoordinator = OnboardingFlowCoordinator(
            navigator = navigator,
            abTest = abTest,
            onboardingFinished = rootCoordinator::onboardingCompleted
    )
    private val newsCoordinator = NewsFlowCoordinator(
            abTest = abTest,
            navigator = navigator
    )
    private val loginCoordinator = LoginFlowCoordinator(navigator = navigator)


    //
    // Init
    //
    init {
        rootCoordinator.onboardingCoordinator = onboardingCoordinator
        rootCoordinator.loginFlowCoordinator = loginCoordinator
        rootCoordinator.newsFlowCoordinator = newsCoordinator
    }


    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        val same = modelClass.toString() == LoginViewModel::class.java.toString()

        val c = modelClass.toString()
        Log.d("ViewModelFactory", "Trying creating '${modelClass.toString()}' and '${LoginViewModel::class.java.toString()}' same $same")
        return if (c == LoginViewModel::class.java.toString())
            LoginViewModel(
                    loginStateMachine = LoginStateMachine(usermanager)
            ) as T
        else if (c == PersonalInteresstsViewModel::class.java.toString()) {

            PersonalInteresstsViewModel(
                    newsRepository = newsRepository,
                    nextNavigation = onboardingCoordinator::onPersonalInterestsSelected

            ) as T
        } else if (c == NewsListViewModel::class.java.toString())
            NewsListViewModel(
                    newsRepository = newsRepository,
                    onItemSelected = newsCoordinator::onNewsDetailsSelected
            ) as T
        else if (c == NewsDetailViewModel::class.java.toString()) {

            NewsDetailViewModel(
                    newsRepository = newsRepository,
                    newsId = 1,
                    onCloseNews = newsCoordinator::onNewsClosed
            ) as T
        } else if (c == IapViewModel::class.java.toString()) {
            IapViewModel(
                    close = { true }
            ) as T
        }
        else throw IllegalArgumentException("No ViewModel registered for $modelClass")
    }
}





