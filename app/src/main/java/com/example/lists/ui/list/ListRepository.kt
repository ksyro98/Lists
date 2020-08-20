package com.example.lists.ui.list

import androidx.lifecycle.LiveData
import com.example.lists.data.Item
import com.example.lists.data.room.ListDao
import javax.inject.Inject

class ListRepository @Inject constructor(private val listDao: ListDao){

    private var listName: String = ""
    var allItems: LiveData<List<Item>> = listDao.getListsItems(listName)

    fun updateAllItems(listName: String){
        this.listName = listName
        allItems = listDao.getListsItems(this.listName)
    }

    suspend fun insert(item: Item){
        listDao.insertListItem(item)
    }

    suspend fun delete(item: Item){
        listDao.deleteItem(item)
    }
}