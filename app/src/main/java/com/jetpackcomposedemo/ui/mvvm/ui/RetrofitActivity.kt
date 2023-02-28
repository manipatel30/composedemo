package com.jetpackcomposedemo.ui.mvvm.ui

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.jetpackcomposedemo.R
import com.jetpackcomposedemo.ui.mvvm.ApiState
import com.jetpackcomposedemo.ui.mvvm.model.Post
import com.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RetrofitActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AddToolbar(mainViewModel)
                }
            }
        }
    }
}

@Composable
fun AddToolbar(mainViewModel: MainViewModel) {
    val activity = (LocalContext.current as? Activity)

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.retrofit_mvvm_compose_label)) },
                navigationIcon = {
                    IconButton(onClick = {
                        activity?.finish()
                    }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                })
        },
    ) {
        GetData(mainViewModel)
    }
}

@Composable
fun GetData(mainViewModel: MainViewModel) {
    when (val result = mainViewModel.response.value) {
        is ApiState.Success -> {
            LazyColumn {
                items(result.data) { response ->
                    EachRow(response)
                }
            }
        }
        is ApiState.Failure -> {
            Text(text = result.error.toString())
        }
        ApiState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier.size(80.dp),
                color = Color.Magenta,
                strokeWidth = 6.dp
            )
        }

        else -> {}
    }
}


@Composable
fun EachRow(post: Post) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            text = post.body,
            modifier = Modifier.padding(10.dp),
            fontStyle = FontStyle.Italic
        )
    }
}