package com.hannesdorfmann.navigation.view.newsdetails

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.navigation.OnBackPressed
import com.hannesdorfmann.navigation.R
import com.hannesdorfmann.navigation.utils.getViewModel
import com.hannesdorfmann.navigation.utils.subscribe
import kotlinx.android.synthetic.main.fragment_detail.*


class NewsDetailFragment : Fragment(), OnBackPressed {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, null, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val vm: NewsDetailViewModel = getViewModel()
        vm.title.subscribe(this) {
            title.text = it
        }
    }


    override fun onBackPressed(): Boolean {
        val vm: NewsDetailViewModel = getViewModel()
        vm.closeNews()
        return true
    }
}