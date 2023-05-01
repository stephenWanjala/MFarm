package com.wantech.mfarm.auth.signIn.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.wantech.mfarm.auth.signIn.LoginSection


@Composable
fun SignInScreen(
    navController: NavHostController, onNavigate: () -> Unit,
    onNavigateToSignUpScreen: (String) -> Unit,
) {
    val accesToken = remember {
        mutableStateOf("")
    }
    val viewModel = hiltViewModel<LoginViewModel>()
    val loginState =viewModel.loginState.collectAsState()
    loginState.value.login?.let {
        accesToken.value =it.access
    }
   LaunchedEffect(key1 = accesToken ){
       if (accesToken.value.isNotEmpty()){
           onNavigate()
       }
   }
    if (accesToken.value.isEmpty()) {


        LoginSection(
            onNavigate = onNavigate,
            onNavigateToSignUpScreen = onNavigateToSignUpScreen,
            navController = navController
        )
    } else{
        onNavigate()
    }
}
