package com.hannesdorfmann.navigation.view.onboarding.personalinteressts

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hannesdorfmann.navigation.R
import com.hannesdorfmann.navigation.utils.getViewModel
import com.hannesdorfmann.navigation.utils.subscribe
import kotlinx.android.synthetic.main.fragment_categories.*

class PersonalInteresstsFragment : Fragment() {

    lateinit var adapter: PersonalInteresstsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_categories, null, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel: PersonalInteresstsViewModel = getViewModel()

        adapter = PersonalInteresstsAdapter(layoutInflater) {
            viewModel.toggleSelected(it)
        }

        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = adapter
        nextButton.setOnClickListener { viewModel.nextClicked() }
        viewModel.allCategories.subscribe(this) {
            adapter.items = it
            adapter.notifyDataSetChanged()
        }

        viewModel.selectedItems.subscribe(this) {
            adapter.selected = it
            adapter.notifyDataSetChanged()
        }
    }
}