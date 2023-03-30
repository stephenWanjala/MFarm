package com.wantech.mfarm.onboarding.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.wantech.mfarm.core.presentation.components.ATextButton
import com.wantech.mfarm.onboarding.domain.model.OnBoardingItem

@Composable
fun OnBoardingScreenItem(
    modifier: Modifier = Modifier,
    onBoardingItem: OnBoardingItem,
    isLastScreen: Boolean = false,
    onSkip: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = onBoardingItem.image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth())
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = onBoardingItem.title,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = onBoardingItem.description,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
            ATextButton(
                onClick = onSkip,
                text = if (isLastScreen) "Finish" else "Skip",
                buttonEnabled = {true},
                modifier = Modifier
            )
        }

    }

}