package com.wantech.mfarm.auth.signUp

import android.content.pm.ActivityInfo
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
    var currentScreen by rememberSaveable {
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
