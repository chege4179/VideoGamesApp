package com.peterchege.gamesapp.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.peterchege.gamesapp.presentation.ui.screens.BottomTabNavigation
import com.peterchege.gamesapp.presentation.ui.screens.search_games_screen.SearchGamesScreen

import com.peterchege.gamesapp.util.Screens

@Composable
fun AppNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screens.BOTTOM_TAB_NAVIGATION
    ){
        composable(
            route = Screens.BOTTOM_TAB_NAVIGATION,

            ){
            BottomTabNavigation(navHostController = navController)
        }
        composable(
            route = Screens.SEARCH_GAMES_SCREEN
        ){
            SearchGamesScreen(navController = navController)

        }
    }


}