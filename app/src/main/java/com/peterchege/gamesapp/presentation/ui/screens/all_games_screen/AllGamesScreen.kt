package com.peterchege.gamesapp.presentation.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.peterchege.gamesapp.presentation.ui.components.GamesCard
import com.peterchege.gamesapp.presentation.ui.components.PlatformCard
import com.peterchege.gamesapp.presentation.ui.screens.all_games_screen.AllGamesScreenViewModel
import com.peterchege.gamesapp.presentation.ui.theme.DarkBlue
import com.peterchege.gamesapp.presentation.ui.theme.MainWhiteColor
import com.peterchege.gamesapp.util.Screens


@OptIn(ExperimentalCoilApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AllGamesScreen(
    navController: NavController,
    viewModel: AllGamesScreenViewModel = hiltViewModel()

) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .fillMaxHeight(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,

                        ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(0.75f),
                            text = "Video Games App"
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            IconButton(
                                onClick = {
                                    navController.navigate(Screens.SEARCH_GAMES_SCREEN)
                                }) {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = "Search",
                                    modifier = Modifier.size(26.dp)

                                )
                            }
                        }
                    }

                },
                backgroundColor = MaterialTheme.colors.primary
            )
        }
        ) {
        val gamesData = viewModel.gamesPager.collectAsLazyPagingItems()
        if (gamesData.itemCount == 0) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize().background(Color.Black),
                columns = GridCells.Fixed(2)

            ) {
                items(gamesData.itemCount) { index ->
                    gamesData[index]?.let {
                        GamesCard(
                            game = it,
                            onNavigateToGameScreen = {

                            })
                    }
                }
                when (gamesData.loadState.append) {
                    is LoadState.NotLoading -> Unit
                    LoadState.Loading -> {
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                    is LoadState.Error -> {
                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),

                                ) {
                                Text(text = (gamesData.loadState.append as LoadState.Error).error.message.toString())

                            }
                        }
                    }
                }
            }
        }

    }
}

