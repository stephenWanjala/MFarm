package com.wantech.mfarm.auth.forgot_password.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wantech.mfarm.core.presentation.components.AButton
import com.wantech.mfarm.core.presentation.components.IConWithText
import com.wantech.mfarm.core.presentation.components.InputTextField
import com.wantech.mfarm.core.util.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(navController: NavController) {
    var emailState by remember {
        mutableStateOf("")
    }
//    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember {
        SnackbarHostState()
    }
    Scaffold(
//        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },

        ) { paddingValue ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue),
            contentAlignment = Alignment.Center
        ) {
            IConWithText(
                modifier = Modifier.align(Alignment.TopCenter),
                text = "Forgot Password",
                onClick = {
                    navController.navigate(Screen.SignIn.route) {
                        popUpTo(Screen.SignIn.route) {
                            inclusive = true
                        }
                    }
                })

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .align(Alignment.Center),
//                contentColor = MaterialTheme.colors.surface,
//                backgroundColor = MaterialTheme.colors.onBackground,
                shape = RoundedCornerShape(12.dp),

                ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
//                        .align(Alignment.Center)
                        .padding(start = 16.dp, end = 16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Please Enter your Email, \nyou will receive a link to reset your password via email",
                        modifier = Modifier
                            .align(CenterHorizontally)
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp),
                        textAlign = TextAlign.Center
                    )
                    InputTextField(
                        textValue = emailState,
                        labelText = "Your Email",
                        onValueChange = { email ->
                            emailState = email

                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Send
                        )
                    )

                    AButton(
                        text = "Send",
                        onClick = {
                            scope.launch {
                                snackBarHostState
                                    .showSnackbar(
                                        message = "Sent, check your email",
                                        actionLabel = "Dismiss",
                                        duration = SnackbarDuration.Long
                                    )
                            }

                            emailState = ""
                        },

                        modifier = Modifier.padding(bottom = 8.dp),
                        buttonEnabled = ({
                            emailState.isNotBlank() &&
                                    android.util.Patterns.EMAIL_ADDRESS.matcher(emailState)
                                        .matches()
                        }
                                )
                    )
                }
            }

        }

    }


}