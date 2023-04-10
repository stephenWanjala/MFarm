package com.wantech.mfarm.auth.signUp

import android.content.pm.ActivityInfo
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.wantech.mfarm.auth.signUp.presentation.components.SignUpProgressIndicator
import com.wantech.mfarm.core.util.LockScreenOrientation


@Composable
fun SignUpScreen(navController: NavController) {
    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    var currentScreen by remember {
        mutableStateOf(0)
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        BackHandler(enabled = currentScreen > 0) {
            currentScreen--
        }

        SignUpProgressIndicator(
            screen = currentScreen,
            modifier = Modifier.align(Alignment.TopCenter)
        )
        Text(text = "Screen $currentScreen",
        modifier = Modifier.clickable {
            currentScreen++
        })

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
