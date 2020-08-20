package com.example.lists.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lists.data.Item
import com.example.lists.data.ItemsList

@Database(entities = [ItemsList::class, Item::class], version = 2, exportSchema = false)
abstract class LocalDatabase : RoomDatabase(){

    abstract fun listDao(): ListDao

}