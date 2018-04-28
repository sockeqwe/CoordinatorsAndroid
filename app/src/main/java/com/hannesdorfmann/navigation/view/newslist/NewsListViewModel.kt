package com.hannesdorfmann.navigation.view.newslist

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.hannesdorfmann.navigation.domain.news.News
import com.hannesdorfmann.navigation.domain.news.NewsRepository
import io.reactivex.disposables.Disposable

class NewsListViewModel(
        private val newsRepository: NewsRepository,
        private var onItemSelected: ((Int) -> Unit)?
) : ViewModel() {

    private lateinit var disposable: Disposable

    val items = MutableLiveData<List<News>>()

    init {
        disposable = newsRepository.getNewsArticles().subscribe { items.value = it }
    }

    fun itemSelected(itemId: Int) {
        onItemSelected!!(itemId)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
        onItemSelected = null
    }
}