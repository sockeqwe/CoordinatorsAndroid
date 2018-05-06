package com.hannesdorfmann.navigation.view.onboarding.personalinterests

import android.graphics.drawable.ColorDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hannesdorfmann.navigation.R
import com.hannesdorfmann.navigation.domain.news.Category

class PersonalInteresstsAdapter(private val inflater: LayoutInflater, private val toggleSelection: (Category) -> Unit) : RecyclerView.Adapter<InteresstViewHolder>() {

    var items: List<Category> = emptyList()
    var selected: Set<Category> = emptySet()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InteresstViewHolder {
        return InteresstViewHolder(inflater.inflate(R.layout.item_category, null, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: InteresstViewHolder, position: Int) {
        val category = items[position]
        holder.text.text = category.name
        holder.itemView.background = if (selected.contains(category)) ColorDrawable(ContextCompat.getColor(holder.itemView.context, R.color.colorAccentOpacity)) else null
        holder.itemView.setOnClickListener {
            toggleSelection(category)
        }
    }
}


class InteresstViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val text = itemView.findViewById<TextView>(R.id.text)

}