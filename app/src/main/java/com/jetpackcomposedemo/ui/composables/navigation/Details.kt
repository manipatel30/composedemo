package com.jetpackcomposedemo.ui.composables.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jetpackcomposedemo.R

@Composable
fun DetailsScreen(navController: NavController? = null) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Details Screen")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController?.navigateUp()
                    }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                elevation = 10.dp
            )
        },
        content = { paddingValues ->
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize(),
            ) {
                Text(
                    text = "Details Screen",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Cursive,
                    color = colorResource(id = R.color.purple_700),
                    modifier = Modifier.padding(paddingValues)
                )
                /*Button(onClick = {
                    navController?.navigateUp()
                }) {
                    Text(text = "Go Back")
                }*/
            }
        }
    )
}