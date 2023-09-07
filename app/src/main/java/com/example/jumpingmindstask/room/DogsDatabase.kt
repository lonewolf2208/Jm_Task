package com.example.jumpingmindstask.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.jumpingmindstask.model.DogsDataItem
import com.example.stackexchange.model.DogsDAO


@Database(entities = [DogsDataItem::class], version = 1)
abstract class DogsDatabase: RoomDatabase() {
    abstract fun dogsDao(): DogsDAO
}