package com.example.lists.ui.main

import android.app.Application
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.lists.data.ItemsList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    application: Application,
    private val repository: MainRepository,
    @Assisted savedStateHandle: SavedStateHandle
): AndroidViewModel(application) {

    val names = repository.allItems

    fun insert(listName: String){
        val itemsList = ItemsList(listName)

        viewModelScope.launch(Dispatchers.IO){
            repository.insert(itemsList)
        }
    }

    fun delete(itemsList: ItemsList) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(itemsList)
    }

}