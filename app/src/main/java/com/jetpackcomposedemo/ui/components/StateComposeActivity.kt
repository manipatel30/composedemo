package com.jetpackcomposedemo.ui.components

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import  com.jetpackcomposedemo.R
import  androidx.lifecycle.viewmodel.compose.viewModel
import com.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme
import com.jetpackcomposedemo.ui.utils.ScaffoldWithTopBar

class StateComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.primary
                ) {
                    ScaffoldWithTopBar(stringResource(R.string.state_compose_label)) {
                        NormalState()
                    }
                }
            }
        }
    }
}

@Composable
fun NormalState(viewModel: StateViewModel = viewModel()) {
    //val name = remember { mutableStateOf("") }
    //val name = rememberSaveable() { mutableStateOf("") } // Configuration change
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (viewModel.name.value.isNotEmpty()) {
            Text(text = viewModel.name.value.toString(), color = Color.Green)
        }

        OutlinedTextField(
            value = viewModel.name.value,
            onValueChange = {
                viewModel.name.value = it
            },
            label = { Text(text = "Name") }
        )

        Spacer(modifier = Modifier.height(20.dp))
        TextFieldLayout()
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextFieldLayout() {
    val context = LocalContext.current

    var name by remember {
        mutableStateOf("")
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = name,
            onValueChange = {
                name = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.sp
            ),
            label = { Text(text = "Name") },
            placeholder = {
                Text(text = "Enter Name")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Account Box"
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "Account Box"
                )
            },
            singleLine = true,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    //Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
                }
            ),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = MaterialTheme.colors.primary,
                unfocusedIndicatorColor = MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
                //backgroundColor = MaterialTheme.colors.surface.copy(alpha = 0.5f)
            ))

        OutlinedTextField(
            value = name,
            modifier = Modifier.padding(10.dp),
            onValueChange = {
                name = it
            })
    }
}

@Composable
fun StateComposeListDemo() {
    val user = User(1)
    val users = remember { mutableStateListOf(user) }
    Box(modifier = Modifier.fillMaxSize()) {
        UsersList(users = users)
        Button(
            modifier = Modifier
                .padding(24.dp)
                .align(Alignment.BottomCenter),
            onClick = {
                users.add(User(2))
            }) {
            Text(text = "Add More")
        }
    }
}

@Composable
fun UsersList(users: List<User>) {
    LazyColumn {
        items(users) { user ->
            UserCard()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStateComposeDemo() {
    Surface(modifier = Modifier.fillMaxSize()) {
        //StateComposeListDemo()
        NormalState()
    }
}