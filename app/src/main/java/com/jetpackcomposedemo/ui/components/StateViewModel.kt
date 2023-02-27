package com.jetpackcomposedemo.ui.components

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class StateViewModel : ViewModel() {
    val name: MutableState<String> = mutableStateOf("")
}