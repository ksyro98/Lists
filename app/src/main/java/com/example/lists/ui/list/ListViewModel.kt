package com.example.lists.ui.list

import android.app.Application
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.lists.data.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel @ViewModelInject constructor(
    application: Application,
    private val repository: ListRepository,
    @Assisted savedStateHandle: SavedStateHandle
): AndroidViewModel(application) {

    var listName: String = ""
        set(value) {
            field = value
            repository.updateAllItems(value)
            allItems = repository.allItems
        }

    var allItems = repository.allItems


    fun insert(itemName: String){
        val item = Item(listName, itemName)
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(item)
        }
    }

    fun delete(item: Item){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(item)
        }
    }

}