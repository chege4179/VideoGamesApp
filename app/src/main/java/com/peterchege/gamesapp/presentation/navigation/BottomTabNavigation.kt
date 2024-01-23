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

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Computer
import androidx.compose.material.icons.outlined.SportsEsports
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.peterchege.gamesapp.core.util.Screens
import com.peterchege.gamesapp.presentation.screens.all_games.AllGamesScreen
import com.peterchege.gamesapp.presentation.screens.all_platforms.AllPlatformsScreen


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BottomTabNavigation(
    navHostController: NavHostController
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavBar(
                items = listOf(
                    BottomNavItem(
                        name="Home",
                        route = Screens.ALL_GAMES_SCREEN  ,
                        icon = Icons.Outlined.SportsEsports
                    ),
                    BottomNavItem(
                        name="Platforms",
                        route = Screens.ALL_PLATFORMS_SCREEN   ,
                        icon = Icons.Outlined.Computer
                    ),


                ),
                navController = navController,
                onItemClick ={
                    navController.navigate(it.route)
                }
            )
        }
    ) { innerPadding ->
        // Apply the padding globally to the whole BottomNavScreensController
        Box(modifier = Modifier.padding(innerPadding)) {
            DashboardNavigation(navController = navController, navHostController = navHostController)
        }

    }
}


@Composable
fun BottomNavBar(
    items:List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick:(BottomNavItem) -> Unit
){
    val backStackEntry = navController.currentBackStackEntryAsState()
    NavigationBar(
        modifier = modifier,

    ) {
        items.forEach{ item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected =selected ,
                onClick = { onItemClick(item) },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        if (item.badgeCount > 0){

                            Icon(
                                imageVector = item.icon,
                                contentDescription =item.name
                            )

                        }else{
                            Icon(
                                imageVector = item.icon,
                                contentDescription =item.name
                            )
                        }
                        if (selected){
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp

                            )
                        }

                    }

                }
            )
        }
    }
}


@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun DashboardNavigation(
    navController: NavHostController,
    navHostController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Screens.ALL_GAMES_SCREEN ){

        composable(
            route = Screens.ALL_GAMES_SCREEN
        ){
            AllGamesScreen(navController = navHostController)
        }
        composable(
            route = Screens.ALL_PLATFORMS_SCREEN
        ){
            AllPlatformsScreen(navController = navHostController)
        }

    }

}


data class BottomNavItem(
    val name:String,
    val route:String,
    val icon: ImageVector,
    val badgeCount:Int = 0,
    )