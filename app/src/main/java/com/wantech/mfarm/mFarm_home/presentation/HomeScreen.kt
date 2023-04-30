package com.wantech.mfarm.mFarm_home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.wantech.mfarm.core.util.Screen
import com.wantech.mfarm.mFarm_home.BottomBarItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
        val snackbarHostState =remember{
            SnackbarHostState()
        }

        val navController = rememberNavController()
        var showBottomBar by remember { mutableStateOf(true) }
        val newBackStackEntry by navController.currentBackStackEntryAsState()
        val route = newBackStackEntry?.destination?.route
        val bottomBarItems: List<BottomBarItem> = listOf(
            BottomBarItem.Home,
            BottomBarItem.Profile,
            BottomBarItem.Settings,

        )
        showBottomBar = route in listOf(
            Screen.HomeView.route,
            Screen.Profile.route,
            Screen.Settings.route,
        )



        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
            bottomBar = {
                if (showBottomBar) {
                    NavigationBar {
                        bottomBarItems.forEach { item ->
                            NavigationBarItem(
                                icon = {
                                    Icon(
                                        item.icon,
                                        contentDescription = item.contentDescription
                                    )
                                },
                                label = { Text(item.contentDescription) },
                                selected = route == item.route,
                                onClick = {
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.startDestinationId) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    }
                }}

            ){paddingValues->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)) {
//                HomeNavigation(navController = navController)
//                NavigationHost(navController = navController, isOnBoarded = isOnBoarded)
            }

        }

    }
}
