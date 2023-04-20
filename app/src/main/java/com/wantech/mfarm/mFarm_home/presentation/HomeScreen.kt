package com.wantech.mfarm.mFarm_home.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.auth0.android.jwt.JWT
import com.wantech.mfarm.auth.signIn.presentation.LoginViewModel

@Composable
fun HomeScreen(navController: NavHostController) {
    val vieModel: LoginViewModel = hiltViewModel()
    Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
//        HomeScreenContent(navController = navController)
        val acceToken = remember {
            mutableStateOf("")
        }
        acceToken.value=vieModel.accesToken.value
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(text = "AccessToken Is ${acceToken.value}")

            val loginState = vieModel.loginState.collectAsState()
            if (loginState.value.error != null) {
                Text(text = loginState.value.error.toString())
            }
        }

    }
}

fun decodeJWT(token: String): JWT {
    return JWT(token)
}