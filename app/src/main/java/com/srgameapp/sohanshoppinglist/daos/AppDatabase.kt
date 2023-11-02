package com.srgameapp.sohanshoppinglist.daos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.srgameapp.sohanshoppinglist.entities.ShoppingTable

@Database(entities = [ShoppingTable::class], version = 2)

@TypeConverters(Converters::class,ShoppingItemConverters::class)

abstract class AppDatabase: RoomDatabase(){
    abstract fun shoppingDao():ShoppingDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "shopping.db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }}
}