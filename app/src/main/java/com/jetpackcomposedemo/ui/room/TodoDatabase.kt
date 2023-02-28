package com.jetpackcomposedemo.ui.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jetpackcomposedemo.ui.room.model.ToDo
import com.jetpackcomposedemo.ui.room.model.ToDoDao

@Database(entities = [ToDo::class], version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun getDao(): ToDoDao
}