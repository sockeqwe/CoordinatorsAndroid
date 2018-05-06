package com.hannesdorfmann.navigation.view.login

import android.os.Bundle
import android.support.transition.TransitionManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.navigation.R
import com.hannesdorfmann.navigation.domain.user.LoginStateMachine
import com.hannesdorfmann.navigation.utils.getViewModel
import com.hannesdorfmann.navigation.utils.gone
import com.hannesdorfmann.navigation.utils.subscribe
import com.hannesdorfmann.navigation.utils.visible
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, null, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel: LoginViewModel = getViewModel()

        login.setOnClickListener {
            viewModel.login(
                    LoginStateMachine.LoginCredentials(
                            username = username.text.toString(),
                            password = password.text.toString()
                    )
            )
        }

        register.setOnClickListener {
            viewModel.signUp()
        }

        forgotPassword.setOnClickListener {
            viewModel.forgotPassword()
        }

        viewModel.state.subscribe(this, this::render)
    }

    private fun render(state: LoginViewState) {
        TransitionManager.beginDelayedTransition(loginContainer)
        when (state) {
            LoginViewState.LoadingState -> {
                loading.visible()
                content.gone()
                error.gone()
            }
            LoginViewState.ShowLoginForm -> {
                loading.gone()
                content.visible()
                error.gone()
            }
            LoginViewState.ShowLoginFormWithErrorState -> {
                loading.gone()
                content.visible()
                error.visible()
            }
        }
    }
}
