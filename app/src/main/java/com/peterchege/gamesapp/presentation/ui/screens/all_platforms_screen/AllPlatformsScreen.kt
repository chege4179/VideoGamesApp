package com.peterchege.gamesapp.presentation.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.peterchege.gamesapp.presentation.ui.components.PlatformCard
import com.peterchege.gamesapp.presentation.ui.screens.all_platforms_screen.AllPlatformsViewModel


@OptIn(ExperimentalCoilApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AllPlatformsScreen(
    navController: NavController,
    viewModel : AllPlatformsViewModel = hiltViewModel()
) {
    val platformsData = viewModel.platformsPager.collectAsLazyPagingItems()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "All Game Platforms")
                }
            )
        }

    ) {
        if (platformsData.itemCount == 0){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CircularProgressIndicator()
            }
        }else{
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(2)

            ) {
                items(platformsData.itemCount) { index ->
                    platformsData[index]?.let {
                        PlatformCard(
                            platform = it,
                            onNavigateToPlatformScreen = {

                            })
                    }
                }
                when (platformsData.loadState.append) {
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
                                Text(text = (platformsData.loadState.append as LoadState.Error).error.message.toString())

                            }
                        }
                    }
                }
            }
        }

    }
}

