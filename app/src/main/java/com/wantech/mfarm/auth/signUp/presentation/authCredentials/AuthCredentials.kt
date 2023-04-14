package com.wantech.mfarm.auth.signUp.presentation.authCredentials

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.wantech.mfarm.core.presentation.components.InputTextField
import com.wantech.mfarm.core.presentation.components.PasswordTextField

@Composable
fun AuthCredentials(modifier: Modifier=Modifier) {
    var emailstate = remember {
        mutableStateOf("")
    }
    var  password = remember {
        mutableStateOf("")
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        InputTextField(
            textValue = emailstate.value,
            labelText = "Email",
            onValueChange = { emailstate.value=it },
            isError = false,
            errorMessage = "Enter valid Email Address"
        )


        PasswordTextField(
            textValue = password.value,
            labelText = "Password",
            placeHolder = "Your Password",

            onValueChange = {
               password.value=it
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
            ),
            errorMessage = "Enter a Strong Password",
            isError = false

        )
    }
}