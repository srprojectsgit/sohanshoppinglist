package com.srgameapp.sohanshoppinglist.daos

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.srgameapp.sohanshoppinglist.entities.ShoppingItem

object ShoppingItemConverters {
    @TypeConverter
    @JvmStatic
    fun fromJson(json: String): List<ShoppingItem> {
        val type = object : TypeToken<MutableList<ShoppingItem>>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    @JvmStatic
    fun toJson(list: List<ShoppingItem>): String {
        return Gson().toJson(list)
    }
}