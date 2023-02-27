package com.jetpackcomposedemo.ui.bottomnavigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.jetpackcomposedemo.R

sealed class NavItem(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val navRoute: String
) {
    object Home : NavItem(R.string.home, android.R.drawable.ic_menu_info_details, NAV_HOME)
    object Feed : NavItem(R.string.feed, android.R.drawable.ic_menu_info_details, NAV_FEED)
    object Fav : NavItem(R.string.fav, android.R.drawable.ic_menu_info_details, NAV_FAV)
    object Profile : NavItem(R.string.profile, android.R.drawable.ic_menu_info_details, NAV_PROFILE)
}