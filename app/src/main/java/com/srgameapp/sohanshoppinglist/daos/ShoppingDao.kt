package com.srgameapp.sohanshoppinglist.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.srgameapp.sohanshoppinglist.entities.ShoppingTable
import org.jetbrains.annotations.Nullable

@Dao
interface ShoppingDao {
    @Nullable
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(shoppingList: ShoppingTable)

    @Nullable
    @Upsert
    fun upsert(shoppingList:ShoppingTable)

    @Query("SELECT* FROM ShoppingTable")
    fun getAllShoppingTables(): List<ShoppingTable>

    @Query("SELECT* FROM ShoppingTable where shoppingId = :id")
    fun getAShoppingTable(id:String): ShoppingTable

    @Query("SELECT shoppingList FROM ShoppingTable")
    fun getAllShoppingItems(): String

    
    @Query("SELECT shoppingId FROM ShoppingTable")
    fun getIds(): List<ShoppingTable>

    @Query("SELECT shoppingList FROM ShoppingTable WHERE shoppingId = :id")
    fun getAShoppingItem(id:String): String

    @Query("DELETE FROM ShoppingTable WHERE shoppingID = :id")
    fun removeShoppingList(id:String)

    @Query("UPDATE shoppingtable Set shoppingList = :updatedList where shoppingId = :id")
    fun updateList(updatedList:ShoppingTable, id:String)
}