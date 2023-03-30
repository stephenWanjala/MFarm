package com.wantech.mfarm.onboarding.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.wantech.mfarm.R
import com.wantech.mfarm.onboarding.domain.model.OnBoardingItem
import com.wantech.mfarm.onboarding.presentation.components.OnBoardingScreenItem
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen() {
    val onBoardingItems = listOf(
        OnBoardingItem(
            image = painterResource(id = R.drawable.farmer_with_healthy_cow),
            title = "Welcome To MFarm ",
            description = "With our App Farmers like you can delivery Milk produce directly to The society\n" +
                    "No more middleman to cut your profits"
        ),

        OnBoardingItem(
            image = painterResource(id = R.drawable.map_with_markers),
            title = "Find A SACCO near You",
            description = "Simply register with saccos in your area to get started\nOur app helps you find SACCOs nearest to your location"
        ),

        OnBoardingItem(
            image = painterResource(id = R.drawable.farmer_happ_with_cash),
            title = "Get Fair Prices for your Milk",
            description = "Be sure with MFarm ,receive fair and reasonable prices for your produce.\nSay Goodbye to exploitation and Hello to more profits"
        ),
    )
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(state = pagerState, count = onBoardingItems.size) { page: Int ->
            when (page) {
                0 -> OnBoardingScreenItem(onBoardingItem = onBoardingItems[page],
                    onSkip = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(page = 1)
                        }
                    })

                1 -> OnBoardingScreenItem(onBoardingItem = onBoardingItems[page],
                    onSkip = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(page = 2)
                        }
                    })
                2 -> OnBoardingScreenItem(onBoardingItem = onBoardingItems[page],
                    isLastScreen = true,
                    onSkip = {

                    })
            }

        }

        HorizontalPagerIndicator(pagerState = pagerState, pageCount = onBoardingItems.size,)

    }
}