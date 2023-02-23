package com.jetpackcomposedemo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpackcomposedemo.ui.composables.*
import com.jetpackcomposedemo.ui.composables.navigation.NavigationComposeActivity
import com.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme

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
                    ShowButtons()
                }
            }
        }
    }
}

@Composable
fun ShowButtons() {
    Column(
        modifier = Modifier
            .wrapContentWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.Top
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Button - 1
        val context = LocalContext.current
        Button(
            onClick = {
                // Start TextComposeActivity
                context.startActivity(Intent(context, TextComposeActivity::class.java))
            },
        ) {
            Text(
                text = stringResource(id = R.string.text_compose_label),
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                color = colorResource(id = R.color.white),
                modifier = Modifier.wrapContentSize(),
            )
        }

        //Button - 2
        Button(
            onClick = {
                // Start LayoutComposeActivity
                context.startActivity(Intent(context, LayoutComposeActivity::class.java))
            },
        ) {
            Text(
                text = stringResource(id = R.string.layout_compose_label),
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                color = colorResource(id = R.color.white),
                modifier = Modifier.wrapContentSize()
            )
        }

        //Button - 3
        Button(
            onClick = {
                // Start RVCardComposeActivity
                context.startActivity(Intent(context, RVCardComposeActivity::class.java))
            },
        ) {
            Text(
                text = stringResource(R.string.recyclerview_demo_label),
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                color = colorResource(id = R.color.white),
                modifier = Modifier.wrapContentSize()
            )
        }


        //Button - 4
        Button(
            onClick = {
                // Start StateComposeActivity
                context.startActivity(Intent(context, StateComposeActivity::class.java))
            },
        ) {
            Text(
                text = stringResource(id = R.string.state_compose_label),
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                color = colorResource(id = R.color.white),
                modifier = Modifier.wrapContentSize()
            )
        }


        //Button - 5
        Button(
            onClick = {
                // Start FragmentComposeActivity
                context.startActivity(Intent(context, FragmentComposeActivity::class.java))
            },
        ) {
            Text(
                text = stringResource(id = R.string.fragment_compose_label),
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                color = colorResource(id = R.color.white),
                modifier = Modifier.wrapContentSize()
            )
        }

        //Button - 6
        Button(
            onClick = {
                // Start XMLInComposeActivity
                context.startActivity(Intent(context, XMLInComposeActivity::class.java))
            },
        ) {
            Text(
                text = stringResource(id = R.string.xml_in_compose_label),
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                color = colorResource(id = R.color.white),
                modifier = Modifier.wrapContentSize()
            )
        }


        //Button - 7
        Button(
            onClick = {
                // Start NavigationComposeActivity
                context.startActivity(Intent(context, NavigationComposeActivity::class.java))
            },
        ) {
            Text(
                text = stringResource(id = R.string.navigation_in_compose_label),
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                color = colorResource(id = R.color.white),
                modifier = Modifier.wrapContentSize()
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeDemoTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            ShowButtons()
        }
    }
}