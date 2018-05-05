package com.hannesdorfmann.navigation.view.onboarding.welcome

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.navigation.R
import com.hannesdorfmann.navigation.utils.getViewModel
import com.hannesdorfmann.navigation.utils.subscribe
import kotlinx.android.synthetic.main.fragment_welcome.*

class WelcomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_welcome, null, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val vm: WelcomeViewModel = getViewModel()
        vm.username.subscribe(this) {
            name.text = "Hey $it"
        }
        next.setOnClickListener {
            vm.onNextClicked()
        }
    }
}