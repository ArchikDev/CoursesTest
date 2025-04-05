package com.example.coursestest.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.coursestest.presentation.course.CourseScreen
import com.example.coursestest.presentation.favorite.FavoriteScreen
import com.example.coursestest.presentation.main.MainScreen
import com.example.coursestest.presentation.profile.ProfileScreen
import com.example.coursestest.presentation.signIn.SignInScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: Any = SignInRoute,
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        },
        popEnterTransition = {
            EnterTransition.None
        },
        popExitTransition = {
            ExitTransition.None
        },
        modifier = modifier
    ) {
        composable<MainRoute> {
            MainScreen()
        }
        composable<CourseRoute> {
            CourseScreen()
        }
        composable<SignInRoute> {
            SignInScreen(
                onSignIn = {
                    navController.navigate(MainRoute)
                }
            )
        }
        composable<FavoriteRoute> {
            FavoriteScreen()
        }
        composable<ProfileRoute> {
            ProfileScreen()
        }
    }
}