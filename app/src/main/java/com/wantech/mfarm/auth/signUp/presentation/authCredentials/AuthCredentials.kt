package com.wantech.mfarm.auth.signUp.presentation.authCredentials

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wantech.mfarm.auth.signUp.SignUpViewModel
import com.wantech.mfarm.auth.signUp.SignupEvent
import com.wantech.mfarm.core.presentation.components.InputTextField
import com.wantech.mfarm.core.presentation.components.PasswordTextField

@Composable
fun AuthCredentials(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    OutlinedCard(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.outlinedCardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Credentials")
            Spacer(modifier = Modifier.height(16.dp))
            InputTextField(
                textValue = state.email,
                labelText = "Email",
                onValueChange = { viewModel.onEvent(SignupEvent.EnteredEmail(it)) },
                isError = state.isEmailError!=null,
                errorMessage = "Enter valid Email Address"
            )


            PasswordTextField(
                textValue = state.password,
                labelText = "Password",
                placeHolder = "Your Password",

                onValueChange = {
                    viewModel.onEvent(event = SignupEvent.EnteredPassword(it))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                ),
                errorMessage = "Enter a Strong Password",
                isError = state.isPasswordError!=null

            )
        }
    }
}