package com.hannesdorfmann.navigation.view.onboarding.personalinteressts

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hannesdorfmann.navigation.R
import com.hannesdorfmann.navigation.domain.news.Category

class PersonalInteresstsAdapter(val inflater: LayoutInflater) : RecyclerView.Adapter<InteresstViewHolder>() {

    var items: List<Category> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InteresstViewHolder {
        return InteresstViewHolder(inflater.inflate(R.layout.item_category, null, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: InteresstViewHolder, position: Int) {
        holder.text.text = items[position].name
    }
}


class InteresstViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val text: TextView

    init {
        text = itemView.findViewById<TextView>(R.id.text)
    }
}