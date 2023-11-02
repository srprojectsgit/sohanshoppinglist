package com.srgameapp.sohanshoppinglist.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.srgameapp.sohanshoppinglist.daos.ShoppingItemConverters

@Entity
data class ShoppingTable(
    @PrimaryKey
    val shoppingId:String,
    @TypeConverters(ShoppingItemConverters::class)
    var shoppingList: List<ShoppingItem>

)
{

}