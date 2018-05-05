package com.hannesdorfmann.navigation.view.newsdetails

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.hannesdorfmann.navigation.domain.news.NewsRepository
import io.reactivex.disposables.Disposable

class NewsDetailViewModel(
        private val newsRepository: NewsRepository,
        private val onCloseNews: () -> Unit
) : ViewModel() {

    val title = MutableLiveData<String>()

    lateinit var disposable: Disposable

    var newsId: Int = 0
        set(value) {
            field = value
            disposable = newsRepository.getNewsArticle(newsId).subscribe {
                title.value = it.title
            }
        }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

    fun closeNews() {
        onCloseNews()
    }

}