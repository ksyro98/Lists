package com.example.lists.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "items",
    primaryKeys = ["list_name", "item_name"],
    foreignKeys = [ForeignKey(
        entity = ItemsList::class,
        parentColumns = ["name"],
        childColumns = ["list_name"],
        onDelete = ForeignKey.CASCADE)])
data class Item(
    @ColumnInfo(name = "list_name") val listName: String,
    @ColumnInfo(name = "item_name") val itemName: String
)