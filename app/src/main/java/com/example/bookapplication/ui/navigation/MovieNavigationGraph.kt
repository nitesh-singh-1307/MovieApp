package com.example.bookapplication.ui.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bookapplication.ui.details.MovieDetailScreen
import com.example.bookapplication.ui.home.HomeScreen
import com.example.bookapplication.utils.BaseUrl.MOVIE_ID

@Composable
fun MovieNavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {

    NavHost(
        navController = navController,
        startDestination = Route.HomeScreen().route,
        modifier = modifier.fillMaxSize()
    ) {

        composable(
            route = Route.HomeScreen().route,
            enterTransition = { fadeIn() + scaleIn() },
            exitTransition = { fadeOut() + scaleOut() }
        ) {

            HomeScreen(
                onMovieClick = {
                    navController.navigate(Route.FileScreen().passArgument(it)) {
                        launchSingleTop = true
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = false
                        }
                    }
                }
            )


        }

        composable(
            route = Route.FileScreen().routeWithArgument,
            arguments = listOf(navArgument(MOVIE_ID) {
                type = NavType.IntType
            }
            )
        ) {
//            val movieId = it.arguments?.getInt(MOVIE_ID)

            MovieDetailScreen(
                onNavigateUp = { navController.navigateUp() },
                onMovieClick = {
                    navController.navigate(Route.FileScreen().passArgument(it)) {
                        launchSingleTop = true
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = false
                        }
                    }
                },
                onActorClick = { }
            )
        }
    }


}