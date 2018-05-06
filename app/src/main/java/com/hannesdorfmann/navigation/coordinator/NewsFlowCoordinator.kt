package com.hannesdorfmann.navigation.coordinator

import com.hannesdorfmann.navigation.domain.ab.AbTest

class NewsFlowCoordinator(
        val abTest: AbTest,
        val navigator: Navigator
) {

    fun onNewsClosed() {
        if (abTest.isB()) {
            navigator.showInAppPurchases()
        } else {
            navigator.closeNews()
        }
    }

    fun onIapClosed(){
        navigator.closeIap()
    }

    fun onNewsDetailsSelected(newsId: Int) {
        navigator.showNewsDetails(newsId)
    }

    fun start() {
        navigator.showNewsList()
    }
}