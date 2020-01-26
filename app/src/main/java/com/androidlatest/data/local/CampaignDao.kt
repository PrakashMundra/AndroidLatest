package com.androidlatest.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.androidlatest.data.entity.Campaign

@Dao
interface CampaignDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(campaigns: List<Campaign>)

    @Query("SELECT * FROM campaign ORDER BY id")
    fun getCampaign(): LiveData<List<Campaign>>
}