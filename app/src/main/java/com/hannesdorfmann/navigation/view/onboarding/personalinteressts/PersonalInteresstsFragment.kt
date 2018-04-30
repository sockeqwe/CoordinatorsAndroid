package com.hannesdorfmann.navigation.view.onboarding.personalinteressts

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.navigation.R
import com.hannesdorfmann.navigation.utils.getViewModel
import com.hannesdorfmann.navigation.utils.subscribe
import kotlinx.android.synthetic.main.fragment_categories.*

class PersonalInteresstsFragment : Fragment() {

    lateinit var adapter: PersonalInteresstsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        adapter = PersonalInteresstsAdapter(inflater)
        val view = inflater.inflate(R.layout.fragment_categories, null, false)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = adapter
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel: PersonalInteresstsViewModel = getViewModel()

        nextButton.setOnClickListener { viewModel.nextClicked() }
        viewModel.selectedItems.subscribe(this) {
            adapter.items = it
        }
    }
}