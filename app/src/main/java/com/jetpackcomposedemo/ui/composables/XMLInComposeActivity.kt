package com.jetpackcomposedemo.ui.composables

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.jetpackcomposedemo.R
import com.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme
import com.jetpackcomposedemo.ui.utils.ScaffoldWithTopBar

class XMLInComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScaffoldWithTopBar(stringResource(id = R.string.xml_in_compose_label)) {
                        XMLInComposeDemo()
                    }
                }
            }
        }
    }
}

@Composable
fun XMLInComposeDemo() {
    val context = LocalContext.current
    AndroidView(
        factory = { View.inflate(it, R.layout.layout1, null) },
        modifier = Modifier.fillMaxSize(),
        update = {
            it.findViewById<View>(R.id.textView).setOnClickListener {
                Toast.makeText(context, "Tap Me!", Toast.LENGTH_SHORT).show()
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun XMLInPreview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        XMLInComposeDemo()
    }
}