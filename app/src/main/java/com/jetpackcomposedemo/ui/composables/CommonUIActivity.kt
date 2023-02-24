package com.jetpackcomposedemo.ui.composables

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.jetpackcomposedemo.R
import com.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme
import com.jetpackcomposedemo.ui.utils.ScaffoldWithTopBar

class CommonUIActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScaffoldWithTopBar(title = stringResource(id = R.string.common_ui_compose_label)) {
                        ShowSwitch()
                    }
                }
            }
        }
    }
}


@Composable
fun ShowSwitch() {

    val isCheckedState = remember {
        mutableStateOf(true)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Switch(
            checked = isCheckedState.value,
            onCheckedChange = {
                isCheckedState.value = it
            }
        )
        Text(
            text = if (isCheckedState.value)
                stringResource(id = R.string.switch_on_label) else
                stringResource(id = R.string.switch_off_label),
            fontSize = 20.sp,
            fontFamily = FontFamily.SansSerif,
            color = colorResource(id = R.color.purple_500),
            fontWeight = FontWeight.Bold
        )
    }

}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        ShowSwitch()
    }
}