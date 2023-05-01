package com.wantech.mfarm.core.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wantech.mfarm.mFarm_home.home.Home
import com.wantech.mfarm.mFarm_home.profile.Profile
import com.wantech.mfarm.mFarm_home.settings.Settings

@Composable
fun HomeNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            Home()
        }
        composable(Screen.Profile.route) {
            Profile()
        }

        composable(Screen.Settings.route) {
            Settings()
        }
    }
}