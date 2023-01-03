package com.peterchege.gamesapp.presentation.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import com.peterchege.gamesapp.presentation.ui.components.PlatformCard
import com.peterchege.gamesapp.presentation.viewModels.AllPlatformsViewModel






@OptIn(ExperimentalCoilApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AllPlatformsScreen(
    navController: NavController,
    viewModel :AllPlatformsViewModel = hiltViewModel()
) {
    val platformsData = viewModel.platformsPager.collectAsLazyPagingItems()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()

    ) {
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
//            if (platformsData.itemCount > 0){
//                items(items = platformsData){ platform ->
//                    platform.let {
//                        PlatformCard(
//                            platform = it!!,
//                            onNavigateToPlatformScreen = {
//
//                            })
//                    }
//                }
//            }

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

