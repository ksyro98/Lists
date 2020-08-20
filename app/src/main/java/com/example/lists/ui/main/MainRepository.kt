package com.example.lists.ui.main

import com.example.lists.data.ItemsList
import com.example.lists.data.room.ListDao
import javax.inject.Inject

class MainRepository @Inject constructor(private val listDao: ListDao) {

    val allItems = listDao.getAllListNames()

    suspend fun insert(itemsList: ItemsList){
        listDao.insertListName(itemsList)
    }

    suspend fun delete(itemsList: ItemsList){
        listDao.deleteList(itemsList)
    }

}