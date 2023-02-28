package com.jetpackcomposedemo.ui.mvvm

import com.jetpackcomposedemo.ui.mvvm.model.Post

sealed class ApiState {
    class Success(val data: List<Post>) : ApiState()
    class Failure(val error: Throwable) : ApiState()
    object Loading : ApiState()
    object Empty : ApiState()
}