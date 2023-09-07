package com.example.jumpingmindstask.utils

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.jumpingmindstask.model.Meta
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class RoomConvertors {

    @TypeConverter
    fun fromString(value: String): Meta{
        val listType = object :
            TypeToken<Meta>() {}.type
        return Gson()
            .fromJson<Meta>(value, listType)
    }

    @TypeConverter
    fun listToString(list: Meta): String {
        val gson = Gson()
        return gson.toJson(list)
    }

}