package com.jetpackcomposedemo.ui.components

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpackcomposedemo.R
import com.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme
import com.jetpackcomposedemo.ui.utils.ScaffoldWithTopBar

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScaffoldWithTopBar(title = stringResource(id = R.string.login_ui_compose_label)) {
                        LoginScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun LoginScreen() {

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    /*val userName = rememberSaveable {
        mutableStateOf("")
    }*/
    val userNameState = remember {
        mutableStateOf("")
    }
    val passwordState = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hello Welcome Please Login!",
            fontFamily = FontFamily.SansSerif,
            style = MaterialTheme.typography.body1
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 5.dp),
            value = userNameState.value,
            singleLine = true,
            onValueChange = {
                userNameState.value = it
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = ""
                )
            },
            label = {
                Text(
                    text = "Username",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 20.sp
                )
            },
            placeholder = {
                Text(
                    text = "Enter username",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 20.sp
                )
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 5.dp),
            value = passwordState.value,
            onValueChange = {
                passwordState.value = it
            },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = ""
                )
            },
            label = {
                Text(
                    text = "Password",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 20.sp
                )
            },
            placeholder = {
                Text(
                    text = "Enter password",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 20.sp
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )

        OutlinedButton(
            onClick = {
                validateCredentials(userNameState.value.trim(), passwordState.value.trim(), context)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 8.dp),
        ) {
            Text(text = "Login")
        }
    }
}


fun validateCredentials(userName: String, password: String, context: Context) {
    if (userName.isNullOrEmpty() || password.isNullOrEmpty()) {
        Toast.makeText(
            context,
            "Please check Username and Password and try again later!",
            Toast.LENGTH_LONG
        ).show()
        return
    }

    if (!(userName == "Test") && !(password == "12345")) {
        Toast.makeText(
            context,
            "Please check Username and Password and try again later!",
            Toast.LENGTH_LONG
        ).show()
        return
    }

    Toast.makeText(
        context,
        "Successfully logged-in!",
        Toast.LENGTH_LONG
    ).show()
}

@Preview(showBackground = true)
@Composable
fun SeePreview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        LoginScreen()
    }
}