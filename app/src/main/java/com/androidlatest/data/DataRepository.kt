package com.androidlatest.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.androidlatest.data.entity.Campaign
import com.androidlatest.data.local.CampaignDao
import com.androidlatest.data.model.CampaignResponse
import com.androidlatest.data.remote.ApiService
import com.androidlatest.utils.Constants
import com.androidlatest.utils.JSonUtils
import com.androidlatest.utils.NetworkUtils
import com.androidlatest.workers.DatabaseWorker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DataRepository private constructor(
    private val context: Context,
    private val campaignDao: CampaignDao
) {
    companion object {
        @Volatile
        private var instance: DataRepository? = null

        fun getInstance(context: Context, campaignDao: CampaignDao) =
            instance ?: synchronized(this) {
                instance ?: DataRepository(context, campaignDao).also {
                    instance = it
                }
            }
    }

    fun getCampaigns(): LiveData<List<Campaign>> {
        if (NetworkUtils.isNetworkConnected(context)) {
            val data: MediatorLiveData<List<Campaign>> = MediatorLiveData()
            ApiService.getInstance()?.getCampaigns()?.enqueue(object : Callback<CampaignResponse> {
                override fun onFailure(call: Call<CampaignResponse>, t: Throwable) {
                    data.value = null
                }

                override fun onResponse(
                    call: Call<CampaignResponse>,
                    response: Response<CampaignResponse>
                ) {
                    if (response.isSuccessful) {
                        val campaignResponse = response.body() as CampaignResponse
                        val campaigns = campaignResponse.metadata?.data
                        data.value = campaigns
                        val campaignsJson = JSonUtils.toJson(campaigns)
                        val builder: Data.Builder = Data.Builder()
                        builder.putString(Constants.WORKER_CAMPAIGN_KEY, campaignsJson)
                        val dataBuilder: Data = builder.build()
                        val workRequest = OneTimeWorkRequest.Builder(DatabaseWorker::class.java)
                            .setInputData(dataBuilder).build()
                        WorkManager.getInstance(context).enqueue(workRequest)
                    }
                }
            })
            return data
        } else
            return campaignDao.getCampaign()
    }
}