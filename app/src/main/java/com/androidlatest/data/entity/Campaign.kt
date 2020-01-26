package com.androidlatest.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "campaign")
class Campaign {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    var name: String? = null
    var description: String? = null
    @Embedded(prefix = "image_")
    var image: Image? = null
}