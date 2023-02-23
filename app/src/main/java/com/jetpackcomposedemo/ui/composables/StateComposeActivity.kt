package com.jetpackcomposedemo.ui.composables

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import  com.jetpackcomposedemo.R
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
                        StateComposeDemo()
                    }
                }
            }
        }
    }
}

@Composable
fun StateComposeDemo() {
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
        StateComposeDemo()
    }
}