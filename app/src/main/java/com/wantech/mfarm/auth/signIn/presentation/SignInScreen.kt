package com.wantech.mfarm.auth.signIn.presentation

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
import com.wantech.mfarm.auth.components.TextInPutSection
import com.wantech.mfarm.auth.data.repositoryImpl.AuthRepositoryImpl
import com.wantech.mfarm.core.util.Screen


@Composable
fun SignInScreen(navController: NavController) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .align(Alignment.Center),
//            contentColor = MaterialTheme.colors.surface,
//            backgroundColor = MaterialTheme.colors.onBackground,
            shape = RoundedCornerShape(12.dp),

            ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
//                    .align(Alignment.Center),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                TextInPutSection(
                    buttonLabel = stringResource(id = R.string.sign_in),
                    onClickLoginButton = { navController.navigate(Screen.Home.route) },
                    onClickToSignUp = {
//                        navController.navigate(Screen.SignUp.route) {
//                            popUpTo(Screen.SignUp.route) {
//                                inclusive = true
//                            }
//                        }
                    },
                    onForgetPassword = {
                        navController.navigate(Screen.ForgotPassword.route) {
                            popUpTo(Screen.ForgotPassword.route) {
                                inclusive = true
                            }
                        }
                    },

                )

            }
        }


    }


}
