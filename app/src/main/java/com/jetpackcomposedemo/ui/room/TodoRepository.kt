package com.jetpackcomposedemo.ui.room

import com.jetpackcomposedemo.ui.room.model.ToDo
import com.jetpackcomposedemo.ui.room.model.ToDoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


class TodoRepository
@Inject
constructor(private val dao: ToDoDao) {

    suspend fun insert(toDo: ToDo) = withContext(Dispatchers.IO) {
        dao.insert(toDo)
    }

    fun getAllTodos(): Flow<List<ToDo>> = dao.getAllTodos()
}