/*
 * Copyright 2023 Games App
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.peterchege.gamesapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.peterchege.gamesapp.presentation.screens.games.GamesScreen
import com.peterchege.gamesapp.presentation.screens.search_games.SearchGamesScreen

import com.peterchege.gamesapp.core.util.Screens

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
        composable(
            route = Screens.GAME_SCREEN + "/{id}",
        ){
            GamesScreen(navController = navController)

        }
    }
}