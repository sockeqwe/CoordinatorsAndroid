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

    companion object {
        private val KEY_NEWS_ID = "NewsId"
        fun newInstance(newsId: Int): NewsDetailFragment {
            val b = Bundle()
            b.putInt(KEY_NEWS_ID, newsId)
            val f = NewsDetailFragment()
            f.arguments = b
            return f
        }
    }

    private var newsId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsId = arguments!!.getInt(KEY_NEWS_ID)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, null, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val vm: NewsDetailViewModel = getViewModel()
        vm.newsId = newsId
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