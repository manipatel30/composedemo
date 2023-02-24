package com.jetpackcomposedemo.ui.composables.bottomnavigation

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jetpackcomposedemo.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme

class BottomNavigationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val activity = (LocalContext.current as? Activity)
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.bottom_navigation_in_compose_label))
                },
                navigationIcon = {
                    IconButton(onClick = {
                        activity?.finish()
                    }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                elevation = 10.dp
            )
        },
        bottomBar = {
            AppBottomNavigation(navController = navController)
        }
    ) { paddingValues ->
        NavHost(navController = navController, startDestination = NAV_HOME) {
            composable(NAV_HOME) { AppScreen(text = "Home Screen") }
            composable(NAV_FAV) { AppScreen(text = "Favorites Screen") }
            composable(NAV_FEED) { AppScreen(text = "Feed Screen") }
            composable(NAV_PROFILE) { AppScreen(text = "Profile Screen") }
        }
    }
}

@Composable
fun AppScreen(text: String) = Surface(modifier = Modifier.fillMaxWidth()) {
    Text(text = text, fontSize = 32.sp)
}