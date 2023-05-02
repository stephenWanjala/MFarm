package com.wantech.mfarm.mFarm_home.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Profile() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Text(text = "Profile", textAlign = TextAlign.Center)
    }
}