package com.jetpackcomposedemo.ui.components

import  android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.jetpackcomposedemo.R
import com.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme
import com.jetpackcomposedemo.ui.utils.ScaffoldWithTopBar
import kotlinx.coroutines.launch

class CommonUIActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    /*val scaffoldState = rememberScaffoldState()
                    Scaffold(scaffoldState = scaffoldState) {
                        CreateSnackBar(scaffoldState = scaffoldState)
                    }*/
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

    RadioButtonUI()
}

@Composable
fun RadioButtonUI() {
    val genderList by remember { mutableStateOf(listOf("Male", "Female", "Other")) }
    var genderState by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            genderList.forEach {
                Row {
                    Text(text = it, modifier = Modifier.align(Alignment.CenterVertically))
                    RadioButton(
                        selected = genderState == it,
                        onClick = {
                            genderState = it
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color.Red
                        )
                    )
                }
            }
        }
        Text(text = genderState)
        CheckBoxUI()
    }
}


@Composable
fun CheckBoxUI() {
    var isChecked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        /*Checkbox(
            checked = isChecked,
            onCheckedChange = {
                isChecked = it
            },
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Blue,
                checkmarkColor = Color.Yellow
            )
        )*/
        Spacer(modifier = Modifier.height(20.dp))
        CustomCheckBox()
        Spacer(modifier = Modifier.height(20.dp))
        DialogUI()
        Spacer(modifier = Modifier.height(20.dp))
        BoxLayoutUI()
    }
}

@Composable
fun CustomCheckBox() {
    var isChecked by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Card(
            elevation = 0.dp,
            shape = RoundedCornerShape(8.dp),
            backgroundColor = Color.White,
            border = BorderStroke(1.dp, Color.Black)
        ) {
            Box(
                modifier = Modifier
                    .background(
                        if (isChecked) Green else White
                    )
                    .clickable {
                        isChecked = !isChecked
                    }
                    .size(25.dp),
                contentAlignment = Alignment.Center
            ) {
                if (isChecked) {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = null,
                        tint = White,
                    )
                }
            }
        }

        Text(
            modifier = Modifier
                .padding(start = 10.dp)
                .align(CenterVertically),
            text = "Accept all terms & conditions"
        )
    }
}


@Composable
fun DialogUI() {
    var isDialog by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Button(onClick = {
            isDialog = true
        }) {
            Text(text = "Click Here to see Dialog")
        }

        Spacer(modifier = Modifier.height(20.dp))
        AlertDialogUI()
    }
    if (isDialog) {
        Dialog(onDismissRequest = {
            isDialog = false
        }) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun AlertDialogUI() {
    var isDialog by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Button(onClick = {
            isDialog = true
        }) {
            Text(text = "Click Here to see Alert Dialog")
        }

        if (isDialog) {
            AlertDialog(onDismissRequest = { isDialog = false },
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text(text = "Alert Dialog")
                },
                text = {
                    Text(text = "This is dialog box content")
                },
                buttons = {
                    Button(
                        onClick = {
                            isDialog = false
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(text = "OK")
                    }
                })
        }
    }
}


@Composable
fun CreateSnackBar(scaffoldState: ScaffoldState) {
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Button(onClick = {
            scope.launch {
                scaffoldState.snackbarHostState.showSnackbar("Hey, How are you ")
            }
        }) {
            Text(text = "Show Snack Bar")
        }
    }
}

@Composable
fun BoxLayoutUI() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Yellow),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Hello World 1")
        Text(
            text = "Hello World 2", color = Red,
            modifier = Modifier.align(Alignment.TopCenter)
        )
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Text(text = "Button")
        }
        Text(
            text = "Hello World 3",
            modifier = Modifier.align(Alignment.BottomCenter)
        )
        Text(
            text = "Hello World 4",
            modifier = Modifier.align(Alignment.TopStart)
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