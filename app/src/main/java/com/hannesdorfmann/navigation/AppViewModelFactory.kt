package com.hannesdorfmann.navigation

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.hannesdorfmann.navigation.coordinator.*
import com.hannesdorfmann.navigation.domain.ab.AbTest
import com.hannesdorfmann.navigation.domain.news.NewsRepository
import com.hannesdorfmann.navigation.domain.user.LoginStateMachine
import com.hannesdorfmann.navigation.domain.user.Usermanager
import com.hannesdorfmann.navigation.view.iap.IapViewModel
import com.hannesdorfmann.navigation.view.login.LoginViewModel
import com.hannesdorfmann.navigation.view.newsdetails.NewsDetailViewModel
import com.hannesdorfmann.navigation.view.newslist.NewsListViewModel
import com.hannesdorfmann.navigation.view.onboarding.personalinterests.PersonalInterestsViewModel
import com.hannesdorfmann.navigation.view.onboarding.welcome.WelcomeViewModel


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
    val rootCoordinator = RootFlowCoordinator(usermanager)
    private val onboardingCoordinator = OnboardingFlowCoordinator(
            navigator = navigator,
            onboardingFinished = rootCoordinator::onboardingCompleted
    )
    private val newsCoordinator = NewsFlowCoordinator(
            abTest = abTest,
            navigator = navigator
    )
    private val loginCoordinator = LoginFlowCoordinator(navigator = navigator)


    init {
        rootCoordinator.onboardingCoordinator = onboardingCoordinator
        rootCoordinator.loginFlowCoordinator = loginCoordinator
        rootCoordinator.newsFlowCoordinator = newsCoordinator
    }


    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        LoginViewModel::class.java -> LoginViewModel(
                loginStateMachine = LoginStateMachine(usermanager),
                onForgotPasswordClicked = loginCoordinator::forgotPassword,
                onSignUpClicked = loginCoordinator::registerNewUser
        )
        PersonalInterestsViewModel::class.java -> PersonalInterestsViewModel(
                newsRepository = newsRepository,
                nextNavigation = onboardingCoordinator::onPersonalInterestsSelected,
                usermanager = usermanager

        )
        NewsListViewModel::class.java -> NewsListViewModel(
                newsRepository = newsRepository,
                onItemSelected = newsCoordinator::onNewsDetailsSelected,
                abTest = abTest,
                userManager = usermanager
        )

        NewsDetailViewModel::class.java ->
            NewsDetailViewModel(
                    newsRepository = newsRepository,
                    onCloseNews = newsCoordinator::onNewsClosed
            )
        IapViewModel::class.java -> IapViewModel(
                close = newsCoordinator::onIapClosed
        )

        WelcomeViewModel::class.java -> WelcomeViewModel(
                usermanager = usermanager,
                onNextClicked = onboardingCoordinator::onWelcomeShown
        )

        else -> throw IllegalArgumentException("No ViewModel registered for $modelClass")
    } as T
}





