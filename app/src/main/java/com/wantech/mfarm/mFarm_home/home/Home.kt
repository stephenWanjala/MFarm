package com.wantech.mfarm.mFarm_home.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Home() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Text(text = "Home", textAlign = TextAlign.Center)
    }
}