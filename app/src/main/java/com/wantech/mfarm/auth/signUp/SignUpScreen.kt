package com.wantech.mfarm.auth.signUp

import android.content.pm.ActivityInfo
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wantech.mfarm.auth.signUp.presentation.CheckForLocationPermission
import com.wantech.mfarm.auth.signUp.presentation.authCredentials.AuthCredentials
import com.wantech.mfarm.auth.signUp.presentation.components.SignUpProgressIndicator
import com.wantech.mfarm.auth.signUp.presentation.locationSacco.ChooseLocationSacco
import com.wantech.mfarm.auth.signUp.presentation.personalDetails.PersonalDetails
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

        CheckForLocationPermission(modifier = Modifier) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                when (currentScreen) {
                    SignUpPageParts.LocationAndSacco.page -> ChooseLocationSacco()
                    SignUpPageParts.PersonalDetails.page -> PersonalDetails()
                    SignUpPageParts.AuthCredentials.page -> AuthCredentials()

                }
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    AnimatedVisibility(visible = currentScreen > 0) {
                        OutlinedButton(onClick = { if (currentScreen > 0) currentScreen-- }) {
                            Text(text = "Back")
                        }
                    }
                    NextButton(currentSegment = currentScreen,
                        onclick = { currentScreen++ },
                        onFinish = {

                        })
                }
            }

        }
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


@Composable
fun NextButton(
    modifier: Modifier = Modifier,
    currentSegment: Int,
    onclick: () -> Unit,
    onFinish: () -> Unit
) {
    val isLastScreen = currentSegment == 2
    val buttonText = if (isLastScreen) "Finish" else "Next"
    OutlinedButton(onClick = {
        if (isLastScreen) onFinish() else onclick.invoke()
    }, modifier = modifier) {
        Text(text = buttonText)
    }
}
