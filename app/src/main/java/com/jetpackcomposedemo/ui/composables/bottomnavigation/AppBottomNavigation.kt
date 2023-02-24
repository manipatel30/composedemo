package com.jetpackcomposedemo.ui.composables.bottomnavigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jetpackcomposedemo.R

@Composable
fun AppBottomNavigation(navController: NavController) {
    val navItems = listOf(NavItem.Home, NavItem.Fav, NavItem.Feed, NavItem.Profile)
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.purple_700)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        navItems.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.navRoute,
                label = { Text(text = stringResource(id = item.title)) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = "icon"
                    )
                },
                onClick = {
                    navController.navigate(item.navRoute)
                }
            )
        }
    }
}
