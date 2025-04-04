package com.example.coursestest.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coursestest.presentation.course.CourseScreen
import com.example.coursestest.presentation.favorite.FavoriteScreen
import com.example.coursestest.presentation.main.MainScreen
import com.example.coursestest.presentation.profile.ProfileScreen
import com.example.coursestest.presentation.signIn.SignInScreen

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
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
//            val viewModel = hiltViewModel<ProjectsViewModel>()

            MainScreen()

//            ProjectsScreen(
//                viewModel = viewModel,
//                onEditProject = { project ->
//                    navActions.navigateToAddEditProject(project)
//                },
//                onAddProject = {
//                    navActions.navigateToAddEditProject(project = Project(
//                        projectId = null,
//                        title = "",
//                        countTasks = 0,
//                        totalTime = 0,
//                        color = Project.projectsColors[0].toArgb()
//                    ))
//                },
//                onClickProject = { project ->
//                    navActions.navigateToTaskList(project)
//                }
//            )
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
//        composable<AddEditProjectRoute>(
//            typeMap = mapOf(
//                typeOf<Project>() to AppCustomNavType.ProjectType
//            )
//        ) {
//            val arg = it.toRoute<AddEditProjectRoute>()
//            AddEditProjectScreen(
//                project = arg.project,
//                onBack = { navController.popBackStack() }
//            )
//        }
//
    }
}