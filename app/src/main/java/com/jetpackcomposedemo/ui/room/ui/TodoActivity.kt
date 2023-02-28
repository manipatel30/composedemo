package com.jetpackcomposedemo.ui.room.ui

import android.app.Activity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.jetpackcomposedemo.ui.room.model.ToDo
import com.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoActivity : ComponentActivity() {

    private val todoViewModel: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    AddToolbar()
                }
            }
        }
    }

    fun insert(title: MutableState<String>, description: MutableState<String>) {
        lifecycleScope.launchWhenStarted {
            if (!TextUtils.isEmpty(title.value) && !TextUtils.isEmpty(description.value)) {
                todoViewModel.insert(
                    ToDo(
                        title = title.value,
                        description = description.value
                    )
                )
                Toast.makeText(this@TodoActivity, "Saved", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@TodoActivity, "Please enter details", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @Composable
    fun AddToolbar() {
        val activity = (LocalContext.current as? Activity)

        val openDialog = remember {
            mutableStateOf(false)
        }
        Scaffold(topBar = {
            TopAppBar(title = {
                Text(text = "Todo App")
            },
            navigationIcon = {
                IconButton(onClick = {
                    activity?.finish()
                }) {
                    Icon(Icons.Filled.ArrowBack, "backIcon")
                }
            })
        }, floatingActionButton = {
            FloatingActionButton(onClick = {
                openDialog.value = true
            }) {
                AddDialogBox(openDialog = openDialog)
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        }) { paddingValues ->
            RecyclerView(todoViewModel)
        }
    }

    @Composable
    fun AddDialogBox(openDialog: MutableState<Boolean>) {
        val title = remember {
            mutableStateOf("")
        }

        val description = remember {
            mutableStateOf("")
        }

        if (openDialog.value) {
            AlertDialog(onDismissRequest = {
                openDialog.value = false
            }, title = { Text(text = "Add Todo") }, text = {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    OutlinedTextField(value = title.value, onValueChange = {
                        title.value = it
                    }, placeholder = {
                        Text(text = "Enter title")
                    }, label = {
                        Text(text = "Enter title")
                    }, modifier = Modifier.padding(10.dp)
                    )

                    OutlinedTextField(value = description.value, onValueChange = {
                        description.value = it
                    }, placeholder = {
                        Text(text = "Enter description")
                    }, label = {
                        Text(text = "Enter description")
                    }, modifier = Modifier.padding(10.dp)
                    )
                }
            }, confirmButton = {
                OutlinedButton(onClick = {
                    insert(title, description)
                    openDialog.value = false
                }) {
                    Text(text = "Save")
                }
            })
        }
    }

    @Composable
    fun EachRow(toDo: ToDo) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            elevation = 2.dp,
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(text = toDo.title, fontWeight = FontWeight.ExtraBold)
                Text(
                    text = toDo.description,
                    fontWeight = FontWeight.Light,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }

    @Composable
    fun RecyclerView(todoViewModel: TodoViewModel) {
        LazyColumn {
            items(todoViewModel.response.value) {
                EachRow(it)
            }
        }
    }

}

