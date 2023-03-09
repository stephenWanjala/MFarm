package com.wantech.mfarm.core.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wantech.mfarm.auth.forgot_password.presentation.ForgotPasswordScreen

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.ForgotPassword.route) {
        composable(Screen.ForgotPassword.route) {
            ForgotPasswordScreen(navController = navController)
        }
    }
}