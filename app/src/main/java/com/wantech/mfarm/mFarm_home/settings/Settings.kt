package com.wantech.mfarm.mFarm_home.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Settings() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Text(text = "Settings", textAlign = TextAlign.Center)
    }
}