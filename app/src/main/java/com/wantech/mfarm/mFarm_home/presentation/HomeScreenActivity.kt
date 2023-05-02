package com.wantech.mfarm.mFarm_home.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.wantech.mfarm.core.util.HomeNavigation
import com.wantech.mfarm.core.util.Screen
import com.wantech.mfarm.mFarm_home.BottomBarItem
import com.wantech.mfarm.ui.theme.MFarmTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class HomeScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MFarmTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


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
                        Screen.Home.route,
                        Screen.Profile.route,
                        Screen.Settings.route,
                    )

                    Scaffold(modifier = Modifier.fillMaxSize(),
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
                                            label = { Text(item.route) },
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
                            }
                        }) { paddingValues ->
                        val unUsedPaddingValues = paddingValues

                       HomeNavigation(navController = navController)


                    }

                }
            }

        }
    }

    }

