package com.hannesdorfmann.navigation.view.newsdetails

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.hannesdorfmann.navigation.domain.news.NewsRepository

class NewsDetailViewModel(
        private val newsRepository: NewsRepository,
        newsId: Int,
        private val onCloseNews: () -> Unit
) : ViewModel() {

    val title = MutableLiveData<String>()

    val disposable = newsRepository.getNewsArticle(newsId).subscribe {
        title.value = it.title
    }


    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

    fun closeNews() {
        onCloseNews()
    }

}