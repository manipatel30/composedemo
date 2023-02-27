package com.jetpackcomposedemo.ui.components

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jetpackcomposedemo.ButtonText
import com.jetpackcomposedemo.R
import com.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme
import com.jetpackcomposedemo.ui.utils.ScaffoldWithTopBar

class ComposeUIActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScaffoldWithTopBar(title = stringResource(id = R.string.ui_compose_label)) {
                        ComposeUI()
                    }
                }
            }
        }
    }
}

@Composable
fun ComposeUI() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(16.dp)
            .wrapContentSize(Alignment.Center)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.Top
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        //Button - 1
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
                // Start CommonUIActivity
                context.startActivity(Intent(context, CommonUIActivity::class.java))
            },
        ) {
            ButtonText(R.string.common_ui_compose_label)
        }

        //Button - 8
        Button(
            onClick = {
                // Start LoginActivity
                context.startActivity(Intent(context, LoginActivity::class.java))
            },
        ) {
            ButtonText(R.string.login_ui_compose_label)
        }

        //Button - 9
        Button(
            onClick = {
                // Start ImagePickerComposeActivity
                context.startActivity(Intent(context, ImagePickerComposeActivity::class.java))
            },
        ) {
            ButtonText(R.string.img_picker_compose_label)
        }

        //Button - 10
        Button(
            onClick = {
                // Start ButtonsUIActivity
                context.startActivity(Intent(context, ButtonsUIActivity::class.java))
            },
        ) {
            ButtonText(R.string.buttons_compose_label)
        }

        //Button - 11
        Button(
            onClick = {
                // Start TabViewComposeActivity
                context.startActivity(Intent(context, TabViewComposeActivity::class.java))
            },
        ) {
            ButtonText(R.string.tabview_compose_label)
        }

        //Button - 12
        Button(
            onClick = {
                // Start ConstraintLayoutComposeActivity
                context.startActivity(Intent(context, ConstraintLayoutComposeActivity::class.java))
            },
        ) {
            ButtonText(R.string.constraint_layout_compose_label)
        }

        //Button - 13
        Button(
            onClick = {
                // Start ToggleButtonComposeActivity
                context.startActivity(Intent(context, ToggleButtonComposeActivity::class.java))
            },
        ) {
            ButtonText(R.string.toggle_button_compose_label)
        }

    }
}