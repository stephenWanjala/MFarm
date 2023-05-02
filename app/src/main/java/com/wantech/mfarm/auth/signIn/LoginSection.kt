package com.wantech.mfarm.auth.signIn

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.wantech.mfarm.auth.components.TextInPutSection
import com.wantech.mfarm.auth.signIn.presentation.LoginViewModel
import com.wantech.mfarm.core.presentation.components.LoadingDialog
import com.wantech.mfarm.core.util.Screen
import com.wantech.mfarm.core.util.asString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginSection(
    onNavigate: () -> Unit,
    onNavigateToSignUpScreen: (String) -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val context = LocalContext.current
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val loginState = viewModel.loginState.collectAsState(initial = LoginState())


    LaunchedEffect(key1 = loginState.value.login) {
        if (loginState.value.login != null && loginState.value.login!!.access.isNotEmpty() && loginState.value.login!!.refresh.isNotEmpty()) {
            onNavigate()
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues: PaddingValues ->
        val unUsedpadding = paddingValues

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .align(Alignment.Center),
                shape = RoundedCornerShape(12.dp),

                ) {


                TextInPutSection(
                    buttonLabel = "Login",
                    onClickLoginButton = {
                        viewModel.onEvent(LoginEvent.Login)

                    },
                    onClickToSignUp = { navController.navigate(Screen.SignUp.route) },
                    onForgetPassword = {
                        navController.navigate(Screen.ForgotPassword.route)
                    },
                    viewModel = viewModel
                )

            }

            AnimatedVisibility(visible = loginState.value.isLoading) {
                if (loginState.value.isLoading) {
                    LoadingDialog()
                }
            }
            if (loginState.value.error != null) {
                LaunchedEffect(key1 = true) {
                    snackbarHostState.showSnackbar(
                        message = loginState.value.error!!.asString(
                            context
                        )
                    )
                }
            }
        }
    }
}