package com.hannesdorfmann.navigation.view.newslist

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.hannesdorfmann.navigation.domain.news.News
import com.hannesdorfmann.navigation.domain.news.NewsRepository
import com.hannesdorfmann.navigation.domain.user.Usermanager
import io.reactivex.disposables.Disposable

class NewsListViewModel(
        newsRepository: NewsRepository,
        private val userManager: Usermanager,
        private var onItemSelected: ((Int) -> Unit)?
) : ViewModel() {

    private val disposable: Disposable

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

    fun logout() {
        userManager.logout().blockingAwait()
    }
}