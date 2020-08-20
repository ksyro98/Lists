package com.example.lists.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lists")
data class ItemsList (
    @PrimaryKey @ColumnInfo(name = "name") val name: String
)