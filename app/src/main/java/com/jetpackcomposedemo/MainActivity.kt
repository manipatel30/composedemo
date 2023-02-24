package com.jetpackcomposedemo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpackcomposedemo.ui.composables.*
import com.jetpackcomposedemo.ui.composables.bottomnavigation.BottomNavigationActivity
import com.jetpackcomposedemo.ui.composables.navigation.NavigationComposeActivity
import com.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                navigationIcon = {
                    IconButton(onClick = {
                        Toast.makeText(context, "Menu button clicked!", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(Icons.Filled.Menu, contentDescription = null)
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

                }
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
    ) { paddingValues ->
        ShowButtons()
    }
}

@Composable
fun ShowButtons() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .wrapContentSize(Alignment.Center),
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.Top
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        //Button - 1
        val context = LocalContext.current
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            onClick = {
                // Start TextComposeActivity
                context.startActivity(Intent(context, TextComposeActivity::class.java))
            },
        ) {
            ButtonText(R.string.text_compose_label)
        }

        //Button - 2
        Button(
            onClick = {
                // Start LayoutComposeActivity
                context.startActivity(Intent(context, LayoutComposeActivity::class.java))
            },
        ) {
            ButtonText(R.string.layout_compose_label)
        }

        //Button - 3
        Button(
            onClick = {
                // Start RVCardComposeActivity
                context.startActivity(Intent(context, RVCardComposeActivity::class.java))
            },
        ) {
            ButtonText(R.string.recyclerview_demo_label)
        }


        //Button - 4
        Button(
            onClick = {
                // Start StateComposeActivity
                context.startActivity(Intent(context, StateComposeActivity::class.java))
            },
        ) {
            ButtonText(R.string.state_compose_label)
        }


        //Button - 5
        Button(
            onClick = {
                // Start FragmentComposeActivity
                context.startActivity(Intent(context, FragmentComposeActivity::class.java))
            },
        ) {
            ButtonText(R.string.fragment_compose_label)
        }

        //Button - 6
        Button(
            onClick = {
                // Start XMLInComposeActivity
                context.startActivity(Intent(context, XMLInComposeActivity::class.java))
            },
        ) {
            ButtonText(R.string.xml_in_compose_label)
        }


        //Button - 7
        Button(
            onClick = {
                // Start NavigationComposeActivity
                context.startActivity(Intent(context, NavigationComposeActivity::class.java))
            },
        ) {
            ButtonText(R.string.navigation_in_compose_label)
        }

        //Button - 8
        Button(
            onClick = {
                // Start BottomNavigationActivity
                context.startActivity(Intent(context, BottomNavigationActivity::class.java))
            },
        ) {
            ButtonText(R.string.bottom_navigation_in_compose_label)
        }

        //Button - 9
        Button(
            onClick = {
                // Start CommonUIActivity
                context.startActivity(Intent(context, CommonUIActivity::class.java))
            },
        ) {
            ButtonText(R.string.common_ui_compose_label)
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