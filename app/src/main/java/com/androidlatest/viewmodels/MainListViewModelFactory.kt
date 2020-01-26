package com.androidlatest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidlatest.data.DataRepository

class MainListViewModelFactory internal constructor(private val repository: DataRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = MainListViewModel(repository) as T
}