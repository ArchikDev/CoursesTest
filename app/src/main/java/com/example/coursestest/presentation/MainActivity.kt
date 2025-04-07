package com.example.coursestest.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.coursestest.presentation.components.BottomBar
import com.example.coursestest.presentation.components.NavigationItem
import com.example.coursestest.presentation.navigation.AppNavGraph
import com.example.coursestest.presentation.navigation.OnBoardRoute
import com.example.coursestest.presentation.theme.CoursesTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoursesTestTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                val isVisibleBottomBar = remember {
                    mutableStateOf(false)
                }

                val pdLeftRightContent = remember { mutableStateOf(16.dp) }

                val itemsRoute = listOf(
                    NavigationItem.Main,
                    NavigationItem.Favorite,
                    NavigationItem.Profile,
                )

                isVisibleBottomBar.value = itemsRoute.any { item ->
                    currentDestination?.hasRoute(item.route::class) == true
                }

                if (currentDestination?.hasRoute(OnBoardRoute::class) == true) {
                    pdLeftRightContent.value = 0.dp
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (isVisibleBottomBar.value) {
                            BottomBar(
                                navController = navController
                            )
                        }
                    }
                ) { innerPadding ->
                    AppNavGraph(
                        navController = navController,
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(
                                start = pdLeftRightContent.value,
                                end = pdLeftRightContent.value,
                                top = 16.dp
                            )
                    )
                }
            }
        }
    }
}