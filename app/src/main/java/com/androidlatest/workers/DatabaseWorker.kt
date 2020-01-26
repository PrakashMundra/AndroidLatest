package com.androidlatest.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.androidlatest.data.entity.Campaign
import com.androidlatest.utils.Constants
import com.androidlatest.utils.InjectorUtils
import com.androidlatest.utils.JSonUtils

class DatabaseWorker(private val context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {
    companion object {
        private val TAG = DatabaseWorker::class.java.simpleName
    }

    override suspend fun doWork(): Result {
        try {
            val database = InjectorUtils.getAppDataBase(context)
            val compaignJson = inputData.getString(Constants.WORKER_CAMPAIGN_KEY)
            val Campaigns = JSonUtils.fromJson(compaignJson!!, Array<Campaign>::class.java).toList()
            database.campaignDao().insertAll(Campaigns)
            return Result.success()
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            return Result.failure()
        }
    }
}