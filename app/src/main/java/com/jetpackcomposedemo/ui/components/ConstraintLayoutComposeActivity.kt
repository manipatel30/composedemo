package com.jetpackcomposedemo.ui.components

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.jetpackcomposedemo.R
import com.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme
import com.jetpackcomposedemo.ui.utils.ScaffoldWithTopBar
import kotlinx.coroutines.NonDisposableHandle.parent

class ConstraintLayoutComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScaffoldWithTopBar(title = stringResource(id = R.string.constraint_layout_compose_label)) {
                        //ConstraintLayoutUI()
                        //ArrangeHorizontally()
                        //LoginPageWithConstraintLayout()
                        //GuideLineUI()
                        //BarrierUI()
                        ChainUI()
                    }
                }
            }
        }
    }
}

@Composable
fun ConstraintLayoutUI() {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (box1, box2, box3) = createRefs()

        Box(
            modifier = Modifier
                .size(150.dp)
                .background(Color.Red)
                .constrainAs(box1) {

                }
        )

        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Yellow)
                .constrainAs(box2) {

                }
        )

        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color.Black)
                .constrainAs(box3) {

                }
        )
    }
}

@Composable
fun ArrangeHorizontally() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (text1, text2, text3) = createRefs()

        Text(
            text = "Text 1",
            modifier = Modifier
                .constrainAs(text1) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )
        Text(text = "Text 2",
            modifier = Modifier
                .constrainAs(text2) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                })
        Text(text = "Text 3",
            modifier = Modifier
                .constrainAs(text3) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                })

    }
}

@Composable
fun LoginPageWithConstraintLayout() {

    var username1 by remember {
        mutableStateOf("")
    }

    var password1 by remember {
        mutableStateOf("")
    }
    val constraint = ConstraintSet {
        val userName = createRefFor("username")
        val password = createRefFor("password")
        val button = createRefFor("button")

        constrain(userName) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
        }

        constrain(password) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(userName.bottom, margin = 10.dp)
        }

        constrain(button) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(password.bottom, margin = 10.dp)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ConstraintLayout(
            constraintSet = constraint
        ) {
            TextField(
                value = username1,
                onValueChange = {
                    username1 = it
                },
                modifier = Modifier.layoutId("username")
            )

            TextField(
                value = password1,
                onValueChange = {
                    password1 = it
                },
                modifier = Modifier.layoutId("password"),
                visualTransformation = PasswordVisualTransformation()
            )

            Button(
                modifier = Modifier.layoutId("button"),
                onClick = {}) {
                Text(text = "Login")
            }
        }
    }
}

@Composable
fun GuideLineUI() {

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        val text1 = createRef()
        val createGuideTop = createGuidelineFromTop(40.dp)
        Text(
            text = "Some content",
            modifier = Modifier.constrainAs(text1) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(createGuideTop)
            }
        )
    }
}

@Composable
fun BarrierUI() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        val (text1, text2, text3) = createRefs()
        val barrierEnd = createEndBarrier(text1, text2)

        Text(text = "Text one content", modifier = Modifier.constrainAs(text1) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)

        })


        Text(text = "Text 2", modifier = Modifier.constrainAs(text2) {
            start.linkTo(parent.start)
            top.linkTo(text1.bottom)

        })


        Text(text = "Text 3", modifier = Modifier.constrainAs(text3) {
            start.linkTo(barrierEnd)
            top.linkTo(text1.bottom)
        })

    }
}

@Composable
fun ChainUI() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        val (text1, text2, text3) = createRefs()
        createHorizontalChain(
            text1,
            text2,
            text3,
            chainStyle = ChainStyle.Spread
        )

        Text(
            text = "Text 1",
            modifier = Modifier.constrainAs(text1) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            }
        )

        Text(
            text = "Text 2",
            modifier = Modifier.constrainAs(text2) {
                start.linkTo(text1.end)
                top.linkTo(parent.top)
                bottom.linkTo(text1.bottom)
            }
        )

        Text(
            text = "Text 3",
            modifier = Modifier.constrainAs(text3) {
                start.linkTo(text2.end)
                top.linkTo(text2.top)
                bottom.linkTo(text2.bottom)
            }
        )
    }
}