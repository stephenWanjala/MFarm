package com.wantech.mfarm.core.util

sealed class Screen(val route:String){
    object Home:Screen("home_screen")
    object SignUp:Screen("sign_up")
    object SignIn:Screen("sign_in")
    object ForgotPassword:Screen("forgot_password")
    object OnBoarding:Screen("on_boarding")

    object Profile:Screen("profile")
    object Settings:Screen("settings")
    object HomeView:Screen("home")



}