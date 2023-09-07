package com.example.jumpingmindstask.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.jumpingmindstask.utils.RoomConvertors
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Entity(tableName = "dogs")
@Parcelize
data class DogsDataItem(
    val breed: String,
    @PrimaryKey val id: Int,
    val img: String,
    val meta:@RawValue Meta,
    val origin: String,
    val url: String
):Parcelable