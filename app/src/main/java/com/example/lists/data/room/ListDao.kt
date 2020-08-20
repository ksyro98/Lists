package com.example.lists.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lists.data.Item
import com.example.lists.data.ItemsList

@Dao
interface ListDao {

    @Query("select * from lists")
    fun getAllListNames(): LiveData<List<ItemsList>>

    @Query("select * from items where list_name = :listName")
    fun getListsItems(listName: String): LiveData<List<Item>>

    @Insert(entity = ItemsList::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertListName(itemsList: ItemsList)

    @Insert(entity = Item::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertListItem(item: Item)

    @Delete(entity = ItemsList::class)
    suspend fun deleteList(itemsList: ItemsList)

    @Delete(entity = Item::class)
    suspend fun deleteItem(item: Item)
}