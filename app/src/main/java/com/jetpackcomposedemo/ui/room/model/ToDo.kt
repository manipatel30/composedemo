package com.jetpackcomposedemo.ui.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todoTable")
data class ToDo(
    val title: String,
    val description: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
