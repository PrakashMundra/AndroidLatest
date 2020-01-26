package com.androidlatest.viewmodels

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.androidlatest.data.DataRepository
import com.androidlatest.data.entity.Campaign


class MainListViewModel internal constructor(repository: DataRepository) : ViewModel() {
    val loading = ObservableBoolean(true)
    val campaignData: LiveData<List<Campaign>> = repository.getCampaigns()
}