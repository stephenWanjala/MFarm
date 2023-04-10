package com.wantech.mfarm.auth.signUp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.wantech.mfarm.auth.signUp.presentation.components.SignUpProgressIndicator


@Composable
fun SignUpScreen(navController: NavController) {
    val currentScreen by remember {
        mutableStateOf(2)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        SignUpProgressIndicator(
            screen = currentScreen,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }


/*
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.Center),
//            contentColor = MaterialTheme.colors.surface,
//            backgroundColor = MaterialTheme.colors.onBackground,
            shape = RoundedCornerShape(12.dp),

            ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
//                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                SignUpTextFields(buttonLabel = stringResource(id = R.string.sign_up),
                    toHome = {
                        navController.navigate( Screen.Home.route) {
                            popUpTo(
                                Screen.Home.route
                            ) {
                                inclusive = true
                            }
                        }
                    },
                    onClickToLogin = {
                        navController.navigate(Screen.SignIn.route) {
                            popUpTo(
                                Screen.SignIn
                                    .route
                            ) {
                                inclusive = true
                            }
                        }
                    })
            }

        }*/


}
