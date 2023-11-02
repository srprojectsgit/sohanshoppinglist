package com.srgameapp.sohanshoppinglist.daos

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MapConverters() {
    private val gson: Gson = Gson()


    @TypeConverter
    fun fromString(value: String): Map<String, Boolean> {
        return Gson().fromJson(value, object : TypeToken<Map<String, Boolean>>() {}.type)
    }


    @TypeConverter
    fun fromMap(map: Map<String, Boolean>): String {
        return Gson().toJson(map)

    }
}