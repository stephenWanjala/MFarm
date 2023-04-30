package com.wantech.mfarm.mFarm_home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarItem(
    val route: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    object Home : BottomBarItem("home", icon = Icons.Default.Home, contentDescription = "Home")
    object Profile : BottomBarItem(
        "profile",
        icon = Icons.Default.Person,
        contentDescription = "Profile"
    )

    object Settings : BottomBarItem(
        "settings",
        icon = Icons.Default.Settings,
        contentDescription = "Settings"
    )
}
