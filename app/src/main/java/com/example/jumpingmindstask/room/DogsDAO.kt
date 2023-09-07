package com.example.stackexchange.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jumpingmindstask.model.DogsDataItem
import kotlinx.coroutines.flow.Flow

@Dao
interface DogsDAO {
    @Query("SELECT * FROM dogs")
    fun getAllDogs(): Flow<List<DogsDataItem>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDogs(cars: List<DogsDataItem>)
    @Query("DELETE FROM dogs")
    suspend fun deleteAllDogs()
}