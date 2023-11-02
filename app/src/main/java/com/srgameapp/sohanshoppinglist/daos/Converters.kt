package com.srgameapp.sohanshoppinglist.daos

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.srgameapp.sohanshoppinglist.entities.ShoppingItem
import com.srgameapp.sohanshoppinglist.entities.ShoppingTable
import java.lang.reflect.Type

class Converters() {
    private val gson: Gson = Gson()
    @TypeConverter
    fun toShoppingTable(json: String): ShoppingTable {
        return Gson().fromJson(json, ShoppingTable::class.java)
    }

    @TypeConverter
    fun fromShoppingTabFle(shoppingTable: ShoppingTable): String {
        return Gson().toJson(shoppingTable)
    }



    //This example defines two type converter methods: one that converts a Date object to a Long object, and one that performs the inverse conversion from Long to Date. Because Room knows how to persist Long objects, it can use these converters to persist Date objects.
    //
    //Next, you add the @TypeConverters annotation to the AppDatabase class so that Room knows about the converter class that you have defined:
    //Kotlin
    //Java
}