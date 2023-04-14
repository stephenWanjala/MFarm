package com.wantech.mfarm.onboarding.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.wantech.mfarm.R
import com.wantech.mfarm.core.util.LockScreenOrientation
import com.wantech.mfarm.core.util.Screen
import com.wantech.mfarm.onboarding.domain.model.OnBoardingItem
import com.wantech.mfarm.onboarding.presentation.components.OnBoardingScreenItem
import com.wantech.mfarm.onboarding.presentation.components.calculateCurrentOffsetForPage
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(
    navController: NavHostController,
    viewModel: UserDataVieModel = hiltViewModel()
) {
    val onBoardingItems = listOf(
        OnBoardingItem(
            image = painterResource(id = R.drawable.boy),
            title = "Welcome To MFarm ",
            description = "With our App Farmers like you can deliver Milk produce directly to The society\n" +
                    "No more middleman to cut your profits"
        ),

        OnBoardingItem(
            image = painterResource(id = R.drawable.map_with_markers),
            title = "Find A SACCO near You",
            description = "Simply register with saccos in your area to get started\nOur app helps you find SACCOs nearest to your location"
        ),

        OnBoardingItem(
            image = painterResource(id = R.drawable.cow),
            title = "Get Fair Prices for your Milk",
            description = "Be sure with MFarm ,receive fair and reasonable prices for your produce.\nSay Goodbye to exploitation and Hello to more profits"
        ),
    )
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    LockScreenOrientation(orientation =Configuration.ORIENTATION_PORTRAIT)
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        HorizontalPager(
            modifier = Modifier
                .weight(10f)
                .padding(bottom = 16.dp),
            count = 3,
            state = pagerState,
            verticalAlignment = Alignment.Top,
        ) { page: Int ->
            OnBoardingScreenItem(
                onBoardingItem = onBoardingItems[page],
                isLastScreen = pagerState.currentPage == 2,
                page = page,
                pagerState = pagerState,
            ) {
                if (pagerState.currentPage != 2) {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(page=page+1,
                        pageOffset = pagerState.calculateCurrentOffsetForPage(page+1))
                    }
                } else {
                    viewModel.updateOnBoarding(true)
                    navController.navigate(Screen.SignIn.route) {
                        navController.popBackStack(
                            route = Screen.OnBoarding.route,
                            inclusive = true
                        )
                    }

                }
            }

        }

        HorizontalPagerIndicator(
            pagerState = pagerState, pageCount = onBoardingItems.size,
            modifier = Modifier.padding(16.dp)
        )

    }
}