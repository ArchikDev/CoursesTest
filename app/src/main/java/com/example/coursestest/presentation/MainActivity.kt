package com.example.coursestest.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.coursestest.presentation.components.BottomBar
import com.example.coursestest.presentation.components.NavigationItem
import com.example.coursestest.presentation.navigation.AppNavGraph
import com.example.coursestest.presentation.theme.CoursesTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.splashCondition
            }
        }

        enableEdgeToEdge()
        setContent {
            CoursesTestTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                val isVisibleBottomBar = remember {
                    mutableStateOf(false)
                }


                val itemsRoute = listOf(
                    NavigationItem.Main,
                    NavigationItem.Favorite,
                    NavigationItem.Profile,
                )

                isVisibleBottomBar.value = itemsRoute.any { item ->
                    currentDestination?.hasRoute(item.route::class) == true
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        AnimatedVisibility(
                            visible = isVisibleBottomBar.value,
                            enter = slideInVertically(
                                initialOffsetY = {
                                    it / 2
                                },
                            ),
                            exit = slideOutVertically(
                                targetOffsetY = {
                                    it / 2
                                },
                            )
                        ) {
                            BottomBar(
                                navController = navController
                            )
                        }
                    }
                ) { innerPadding ->
                    AppNavGraph(
                        startDestination = viewModel.startDestination,
                        navController = navController,
                        modifier = Modifier
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}