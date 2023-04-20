package com.wantech.mfarm.onboarding.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.wantech.mfarm.core.presentation.components.ATextButton
import com.wantech.mfarm.onboarding.domain.model.OnBoardingItem

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreenItem(
    modifier: Modifier = Modifier,
    onBoardingItem: OnBoardingItem,
    isLastScreen: Boolean = false,
    pagerState:PagerState,
    page:Int,
    onSkip: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .pagerCubeInDepthTransition(page, pagerState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.5f),
            painter = onBoardingItem.image,
            contentDescription = "Pager Image"
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = onBoardingItem.title,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .padding(top = 20.dp),
            text = onBoardingItem.description,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(6.dp))
        ATextButton(
            text = if (isLastScreen) "Finish" else "Skip",
            onClick = onSkip,
            modifier = Modifier,
            buttonEnabled = { true })

    }

}