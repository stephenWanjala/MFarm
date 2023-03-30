package com.wantech.mfarm.onboarding.domain.model

import androidx.compose.ui.graphics.painter.Painter

data class OnBoardingItem(
    val image: Painter,
    val title: String,
    val description: String
)