package com.androidlatest.data.remote

import com.androidlatest.data.model.CampaignResponse
import com.androidlatest.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface ApiService {
    companion object {
        @Volatile
        private var retrofit: Retrofit? = null

        fun getInstance(): ApiService? {
            retrofit ?: synchronized(this) {
                retrofit = buildRetrofit()
            }
            return retrofit?.create(ApiService::class.java)
        }

        private fun buildRetrofit(): Retrofit {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC
            val client = OkHttpClient.Builder().addInterceptor(logger)
                .connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build()
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

    @GET("cms/test/data.json")
    fun getCampaigns(): Call<CampaignResponse>
}