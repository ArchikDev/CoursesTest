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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.coursestest.presentation.components.BottomBar
import com.example.coursestest.presentation.navigation.AppNavGraph
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

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
//                        if (isVisibleBottomBar.value) {
//                            BottomBar(
//                                navController = navController
//                            )
//                        }
                        BottomBar(
                            navController = navController
                        )
                    }
                ) { innerPadding ->
                    AppNavGraph(
                        navController = navController,
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                                top = 16.dp
                            )
                    )
                }
            }
        }
    }
}