package com.hannesdorfmann.navigation.view.iap

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.navigation.OnBackPressed
import com.hannesdorfmann.navigation.R
import com.hannesdorfmann.navigation.utils.getViewModel
import kotlinx.android.synthetic.main.fragment_purchase.*

open class IapNewsFragment : Fragment(), OnBackPressed {

    private lateinit var vm: IapViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_purchase, null, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vm = getViewModel()
        purchase.setOnClickListener {
            vm.onBuyingClicked()
        }
    }

    override fun onBackPressed(): Boolean {
        vm.onBackPressed()
        return true
    }
}