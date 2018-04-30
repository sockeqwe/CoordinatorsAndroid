package com.hannesdorfmann.navigation.view.onboarding.personalinteressts

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.hannesdorfmann.navigation.domain.news.Category
import com.hannesdorfmann.navigation.domain.news.NewsRepository
import io.reactivex.disposables.Disposable

class PersonalInteresstsViewModel(
        private val newsRepository: NewsRepository,
        private var nextNavigation: (() -> Unit)?
) : ViewModel() {

    val selectedItems = MutableLiveData<List<Category>>()
    private val disposable: Disposable


    init {
        disposable =
                newsRepository.getCatefories()
                        .subscribe {
                            selectedItems.value = it
                        }
    }

    fun nextClicked() {
        nextNavigation!!()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
        nextNavigation = null
    }
}