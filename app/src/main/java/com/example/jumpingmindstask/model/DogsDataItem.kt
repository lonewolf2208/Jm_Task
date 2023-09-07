package com.example.jumpingmindstask.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dogs")
data class DogsDataItem(
    val breed: String,
    @PrimaryKey val id: Int,
    val img: String,
    val origin: String,
    val url: String
)