package com.hannesdorfmann.navigation.view.onboarding.personalinterests

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.hannesdorfmann.navigation.domain.news.Category
import com.hannesdorfmann.navigation.domain.news.NewsRepository
import com.hannesdorfmann.navigation.domain.user.Usermanager
import io.reactivex.disposables.Disposable

class PersonalInterestsViewModel(
        private val newsRepository: NewsRepository,
        private val usermanager: Usermanager,
        private var nextNavigation: (() -> Unit)?
) : ViewModel() {

    val selectedItems = MutableLiveData<Set<Category>>()
    val allCategories = MutableLiveData<List<Category>>()
    private val disposable: Disposable


    init {
        disposable =
                newsRepository.getCatefories()
                        .subscribe {
                            allCategories.value = it
                        }

        selectedItems.value = emptySet()
    }

    fun nextClicked() {
        usermanager.markOnboardingComplete()
        nextNavigation!!()
    }

    fun toggleSelected(category: Category) {
        val items = selectedItems.value!!
        val updated = if (items.contains(category)) {
            // Remove category from items
            items.filter { it == category }.toHashSet()
        } else {
            // add category
            HashSet(items).also { it.add(category) }
        }
        selectedItems.value = updated
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
        nextNavigation = null
    }
}