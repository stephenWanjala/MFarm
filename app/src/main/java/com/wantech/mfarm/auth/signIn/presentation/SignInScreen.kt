package com.wantech.mfarm.auth.signIn.presentation

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.wantech.mfarm.auth.signIn.LoginSection
import com.wantech.mfarm.core.util.Screen


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
           navController.navigate(Screen.Home.route)
       }
   }
    if (accesToken.value.isEmpty()) {


        LoginSection(
            onNavigate = onNavigate,
            onNavigateToSignUpScreen = onNavigateToSignUpScreen,
            navController = navController
        )
    }
}
