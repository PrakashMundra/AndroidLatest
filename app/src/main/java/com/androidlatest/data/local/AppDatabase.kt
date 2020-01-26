package com.androidlatest.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.androidlatest.data.entity.Campaign
import com.androidlatest.utils.Constants

@Database(entities = [Campaign::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            instance ?: synchronized(this) {
                instance = buildDatabase(context)
            }
            return instance
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, Constants.DATABASE_NAME)
                .build()
        }
    }

    abstract fun campaignDao(): CampaignDao
}