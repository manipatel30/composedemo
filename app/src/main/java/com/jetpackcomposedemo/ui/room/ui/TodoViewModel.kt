package com.jetpackcomposedemo.ui.room.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpackcomposedemo.ui.room.TodoRepository
import com.jetpackcomposedemo.ui.room.model.ToDo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel
@Inject
constructor(private val todoRepository: TodoRepository) : ViewModel() {

    val response: MutableState<List<ToDo>> = mutableStateOf(listOf())

    init {
        getAllTodos()
    }

    fun insert(toDo: ToDo) = viewModelScope.launch {
        todoRepository.insert(toDo)
    }

    fun getAllTodos() = viewModelScope.launch {
        todoRepository.getAllTodos()
            .catch { e ->
                Log.e("Main", e.message.toString())
            }.collect {
                response.value = it
            }
    }

}