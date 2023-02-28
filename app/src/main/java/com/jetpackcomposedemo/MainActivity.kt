package com.jetpackcomposedemo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpackcomposedemo.ui.components.*
import com.jetpackcomposedemo.ui.bottomnavigation.BottomNavigationActivity
import com.jetpackcomposedemo.ui.mvvm.ui.RetrofitActivity
import com.jetpackcomposedemo.ui.navigation.NavigationComposeActivity
import com.jetpackcomposedemo.ui.room.ui.TodoActivity
import com.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme
import com.jetpackcomposedemo.ui.viewpager.ViewPagerComposeActivity
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TopBar()
                    //ShowButtons()
                }
            }
        }
    }
}

@Composable
fun TopBar() {
    val context = LocalContext.current

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                        //Toast.makeText(context, "Menu button clicked!", Toast.LENGTH_SHORT).show()

                    }) {
                        Icon(Icons.Filled.Menu, contentDescription = null, tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = {
                        Toast.makeText(
                            context,
                            "Notifications menu button clicked!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }) {
                        Icon(Icons.Filled.Notifications, contentDescription = null)
                    }

                    IconButton(onClick = {
                        Toast.makeText(context, "Search menu button clicked!", Toast.LENGTH_SHORT)
                            .show()
                    }) {
                        Icon(Icons.Filled.Search, contentDescription = null)
                    }
                },
            )
        },
        floatingActionButton = {
            var clickCount by remember { mutableStateOf(0) }
            ExtendedFloatingActionButton(
                text = { Text(text = "Show snackbar") },
                onClick = {
                    // show snackbar as a suspend function
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            "Snackbar # ${++clickCount}"
                        )
                    }
                }
            )
            /* FloatingActionButton(
                 onClick = {
                     Toast.makeText(context, "Add FAB clicked!", Toast.LENGTH_SHORT)
                         .show()
                 }
             ) {
                 Icon(Icons.Filled.Add, contentDescription = null)
             }*/
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        drawerContent = {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                IconButton(onClick = {
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                }) {
                    Icon(Icons.Default.Close, contentDescription = null, tint = Color.Blue)
                }
                Text(text = "Drawer Content")
            }

        },
    ) { paddingValues ->
        ShowButtons()
    }
}

@Composable
fun ShowButtons() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.Top
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        //Button - 1
        Button(
            onClick = {
                // Start ComposeUIActivity
                context.startActivity(Intent(context, ComposeUIActivity::class.java))
            },
        ) {
            ButtonText(R.string.ui_compose_label)
        }

        //Button - 2
        Button(
            onClick = {
                // Start NavigationComposeActivity
                context.startActivity(Intent(context, NavigationComposeActivity::class.java))
            },
        ) {
            ButtonText(R.string.navigation_in_compose_label)
        }

        //Button - 3
        Button(
            onClick = {
                // Start BottomNavigationActivity
                context.startActivity(Intent(context, BottomNavigationActivity::class.java))
            },
        ) {
            ButtonText(R.string.bottom_navigation_in_compose_label)
        }


        //Button - 4
        Button(
            onClick = {
                // Start ViewPagerComposeActivity
                context.startActivity(Intent(context, ViewPagerComposeActivity::class.java))
            },
        ) {
            ButtonText(R.string.viewpager_compose_label)
        }

        //Button - 5
        Button(
            onClick = {
                // Start RetrofitActivity
                context.startActivity(Intent(context, RetrofitActivity::class.java))
            },
        ) {
            ButtonText(R.string.retrofit_mvvm_compose_label)
        }

        //Button - 6
        Button(
            onClick = {
                // Start TodoActivity
                context.startActivity(Intent(context, TodoActivity::class.java))
            },
        ) {
            ButtonText(R.string.room_mvvm_compose_label)
        }
    }
}


@Composable
fun ButtonText(@StringRes btnText: Int) {
    Text(
        text = stringResource(id = btnText),
        fontSize = 20.sp,
        fontFamily = FontFamily.SansSerif,
        color = colorResource(id = R.color.white),
        modifier = Modifier.wrapContentSize()
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeDemoTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            //ShowButtons()
            TopBar()
        }
    }
}