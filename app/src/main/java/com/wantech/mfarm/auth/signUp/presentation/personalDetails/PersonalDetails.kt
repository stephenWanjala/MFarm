package com.wantech.mfarm.auth.signUp.presentation.personalDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wantech.mfarm.auth.signUp.SignUpViewModel
import com.wantech.mfarm.core.presentation.components.InputTextField

@Composable
fun PersonalDetails(modifier: Modifier = Modifier,
viewModel:SignUpViewModel= hiltViewModel()) {
    val name = remember {
        mutableStateOf("")
    }
    val location = remember {
        mutableStateOf("")
    }
    val phone= remember {
        mutableStateOf("")
    }
    OutlinedCard(
        elevation = CardDefaults.outlinedCardElevation(defaultElevation = 4.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Your Personal Details Here")
            Spacer(modifier = Modifier.height(16.dp))
            InputTextField(
                textValue =viewModel.state.value.selectedSacco?.location?:"",
                labelText = "Location",

                onValueChange = { location.value },
                readOnly = true

                )
            InputTextField(
                textValue = name.value,
                labelText = "Your Name",
                onValueChange = { name.value=it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Next
                )

                )

            InputTextField(
                textValue = phone.value,
                labelText = "Phone Number",
                onValueChange = { phone.value=it },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Phone
                ),
                placeHolder = "+254...."

                )

        }
    }
}