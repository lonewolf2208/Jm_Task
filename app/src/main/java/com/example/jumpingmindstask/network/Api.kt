package com.example.jumpingmindstask.network

import com.example.jumpingmindstask.model.DogsData
import com.example.jumpingmindstask.model.DogsDataItem
import retrofit2.http.GET

interface Api {
    @GET("dog_breeds/")
    suspend fun getData():List<DogsDataItem>
}