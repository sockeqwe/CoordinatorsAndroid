package com.hannesdorfmann.navigation.domain.news

import io.reactivex.Observable

class NewsRepository {

    fun getNewsArticles(): Observable<List<News>> =
            Observable.fromCallable {
                val news: List<News> = (1..30).map {
                    News(
                            id = it,
                            title = "News $it",
                            text = "",
                            category = Category("Foo")
                    )
                }
                news
            }


    fun getNewsArticle(id: Int): Observable<News> = getNewsArticles()
            .map { it.filter { it.id == id }.first() }

    fun getCatefories(): Observable<List<Category>> =
            Observable.fromCallable {
                val news: List<Category> = (1..10).map {
                    Category("Category $it")
                }
                news
            }
}

