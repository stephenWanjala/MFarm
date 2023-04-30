package com.wantech.mfarm.auth.signUp.presentation.locationSacco

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wantech.mfarm.auth.signUp.SignUpViewModel
import com.wantech.mfarm.core.domain.model.Sacco
import com.wantech.mfarm.core.presentation.components.SaccoSpinner
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ChooseLocationSacco(
    modifier: Modifier = Modifier, viemodel: SignUpViewModel = hiltViewModel()
) {
val saccoList = remember {
    mutableStateListOf<Sacco>()
}
val saccos =viemodel.saccos.collectAsState()
    LaunchedEffect(key1 = true){
        viemodel.saccos.collectLatest {
           saccoList.clear()
            saccoList.addAll(it)
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        ElevatedCard(
            modifier = modifier
                .fillMaxWidth(),
//                .padding(16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.elevatedCardColors()
        ) {
            Text(
                text = "Select Sacco",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            SaccoSpinner(list = saccos.value, isEnabled = { true },
                onSelectionChanged = { sacco ->

                })

        }
    }
}


