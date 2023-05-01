package com.wantech.mfarm.core.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wantech.mfarm.auth.forgot_password.presentation.ForgotPasswordScreen
import com.wantech.mfarm.auth.signIn.presentation.SignInScreen
import com.wantech.mfarm.auth.signUp.SignUpScreen
import com.wantech.mfarm.onboarding.presentation.OnBoardingScreen

@Composable
fun NavigationHost(
    navController: NavHostController,
    startDestination: String,
    onNavigate: () -> Unit
) {

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.OnBoarding.route) {
            OnBoardingScreen(navController = navController)
        }

        composable(Screen.SignIn.route) {
            SignInScreen(navController = navController,
                onNavigate = onNavigate,
                onNavigateToSignUpScreen = {
                    navController.navigate(Screen.SignUp.route)
                })
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(navController = navController)
        }
        composable(Screen.ForgotPassword.route) {
            ForgotPasswordScreen(navController = navController)
        }


    }
}