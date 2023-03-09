package com.wantech.mfarm.auth.signUp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wantech.mfarm.R
import com.wantech.mfarm.auth.signUp.presentation.components.SignUpTextFields
import com.wantech.mfarm.core.util.Screen


@Composable
fun SignUpScreen(navController: NavController) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

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

        }
    }
}
