package com.hannesdorfmann.navigation.view.newslist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.hannesdorfmann.navigation.R
import com.hannesdorfmann.navigation.domain.news.News
import com.hannesdorfmann.navigation.utils.getViewModel
import com.hannesdorfmann.navigation.utils.subscribe
import kotlinx.android.synthetic.main.fragment_newslist.*
import kotlinx.android.synthetic.main.item_news.view.*

class NewsListFragment : Fragment() {

    private lateinit var adapter: NewsListAdapter
    private lateinit var vm: NewsListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_newslist, null, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        vm = getViewModel()

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = NewsListAdapter(layoutInflater, vm::itemSelected)
        recyclerView.adapter = adapter

        vm.items.subscribe(this) {
            adapter.items = it
            adapter.notifyDataSetChanged()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                vm.logout()
                true
            }
            R.id.toggleABTestGroup -> {
                vm.toggleABTestGroupAssignment()
                true
            }
            else -> false
        }
    }
}


class NewsListAdapter(val inflater: LayoutInflater, val click: (Int) -> Unit) : RecyclerView.Adapter<NewsListViewHolder>() {

    var items = emptyList<News>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder = NewsListViewHolder(inflater.inflate(R.layout.item_news, null, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        holder.itemView.title.text = items[position].title
        holder.itemView.setOnClickListener { click(items[position].id) }
    }
}

class NewsListViewHolder(view: View) : RecyclerView.ViewHolder(view)