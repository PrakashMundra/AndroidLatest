package com.androidlatest.utils

import android.content.Context
import com.androidlatest.data.DataRepository
import com.androidlatest.data.local.AppDatabase
import com.androidlatest.data.local.CampaignDao
import com.androidlatest.viewmodels.MainListViewModelFactory

object InjectorUtils {
    private fun getDataRepository(context: Context, campaignDao: CampaignDao): DataRepository {
        return DataRepository.getInstance(context, campaignDao)
    }

    fun getAppDataBase(context: Context): AppDatabase {
        return AppDatabase.getInstance(context)!!
    }

    fun provideMainListViewModelFactory(context: Context): MainListViewModelFactory {
        val database = getAppDataBase(context)
        val repository = getDataRepository(context, database.campaignDao())
        return MainListViewModelFactory(repository)
    }
}