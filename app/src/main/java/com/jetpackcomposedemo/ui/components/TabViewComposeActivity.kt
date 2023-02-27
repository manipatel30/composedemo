package com.jetpackcomposedemo.ui.components

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.jetpackcomposedemo.R
import com.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme
import com.jetpackcomposedemo.ui.utils.ScaffoldWithTopBar
import kotlinx.coroutines.launch

class TabViewComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScaffoldWithTopBar(title = stringResource(id = R.string.tabview_compose_label)) {
                        TabViewUI()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabViewUI() {
    val pagerState = rememberPagerState()
    val currentPage = pagerState.currentPage
    val scope = rememberCoroutineScope()
    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = currentPage,
            backgroundColor = Color.Yellow,
            /*divider = {
                TabRowDefaults.Divider(
                    thickness = 2.dp,
                    color = Color.Black
                )
            },
            indicator = {
                TabRowDefaults.Indicator(
                    color = Color.Gray.copy(alpha = 0.5f)
                )
            }*/
        ) {
            tabList.forEachIndexed { index, tabData ->
                Tab(
                    modifier = Modifier.padding(10.dp),
                    selected = currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }) {
                    Text(text = tabData.tab)
                }
            }
        }

        HorizontalPager(
            count = tabList.size,
            state = pagerState
        ) { index ->
            Text(text = tabList[index].description)
        }
    }
}

data class TabData(
    val tab: String,
    val description: String
)

val tabList = listOf(
    TabData(tab = "Home", description = "This is Home Page"),
    TabData(tab = "Images", description = "This is Image Page"),
    TabData(tab = "Videos", description = "This is Video Page")
)