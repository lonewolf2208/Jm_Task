package com.example.jumpingmindstask.repository

import com.example.jumpingmindstask.network.Api
import com.example.jumpingmindstask.room.DogsDatabase
import com.example.jumpingmindstask.utils.networkBoundResource
import javax.inject.Inject

class HomeRepository@Inject constructor(
    private val api: Api,
    private val db: DogsDatabase) {
    private val dogsDao = db.dogsDao()

    fun getCars() = networkBoundResource(
        // Query to return the list of all cars
        query = {
            dogsDao.getAllDogs()
        },

        fetch = {
            api.getData()
        },

        // Save the results in the table.
        // If data exists, then delete it
        // and then store.
        saveFetchResult = {
            dogsDao.deleteAllDogs()
            dogsDao.insertDogs(it)
        }
    )
}