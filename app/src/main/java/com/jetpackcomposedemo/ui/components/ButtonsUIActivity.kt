package com.jetpackcomposedemo.ui.components

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jetpackcomposedemo.R
import com.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme
import com.jetpackcomposedemo.ui.utils.ScaffoldWithTopBar

class ButtonsUIActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScaffoldWithTopBar(title = stringResource(id = R.string.buttons_compose_label)) {
                        ShowButtons()
                    }
                }
            }
        }
    }
}

@Composable
fun ShowButtons() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { /*TODO*/ },
            elevation = ButtonDefaults.elevation(
                pressedElevation = 10.dp
            ),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(2.dp, color = Color.Red),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Yellow,
                contentColor = Color.Black
            )
        ) {
            Text(text = "Submit Button")
        }

        TextButton(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.textButtonColors(

            )
        ) {
            Text(text = "Text Button")
        }

        OutlinedButton(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.outlinedButtonColors()
        ) {
            Text(text = "Outlined Button")
        }
    }

}