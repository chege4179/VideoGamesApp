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
package com.peterchege.gamesapp.presentation.screens.games

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage

import com.peterchege.gamesapp.presentation.components.GamePlatformCard

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GamesScreen(
    navController: NavController,
    viewModel: GamesScreenViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    viewModel.game.value?.let { game ->
                        Text(
                            text = game.name_original,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontSize = 17.sp
                        )
                    }
                }
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (viewModel.isLoading.value) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            viewModel.game.value?.let { game ->
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    if (game.background_image == null && game.background_image_additional == null){
                        item{
                            Text(text = "No Image available")
                        }

                    }else{
                        if(game.background_image_additional == null && game.background_image != null){
                            val images = listOf<String>(game.background_image)
                            item {
                                val pagerState1 = rememberPagerState(initialPage = 0, pageCount = { images.size })
                                val coroutineScope = rememberCoroutineScope()
                                HorizontalPager(
                                    state = pagerState1
                                ) { image ->
                                    Box(
                                        modifier = Modifier.fillMaxWidth()
                                    ){
                                        SubcomposeAsyncImage(
                                            model = images[image],
                                            loading = {
                                                Box(modifier = Modifier.fillMaxSize()) {
                                                    CircularProgressIndicator(
                                                        modifier = Modifier.align(
                                                            Alignment.Center
                                                        )
                                                    )
                                                }
                                            },
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(300.dp),
                                            contentDescription = "Game Images"
                                        )
                                        Box(
                                            modifier = Modifier
                                                .padding(10.dp)
                                                .width(45.dp)
                                                .align(Alignment.TopEnd)
                                                .height(25.dp)
                                                .clip(RoundedCornerShape(15.dp))
                                                .background(Color.White)

                                        ){
                                            Text(
                                                modifier = Modifier
                                                    .align(Alignment.Center)
                                                    .padding(horizontal = 3.dp),
                                                textAlign = TextAlign.Start,
                                                fontSize = 17.sp,
                                                text = "${image + 1}/${images.size}"
                                            )
                                        }
                                    }
                                }
                            }
                        }else if(game.background_image_additional != null && game.background_image != null){
                            val images = listOf<String>(game.background_image,game.background_image_additional)
                            item {
                                val pagerState1 = rememberPagerState(initialPage = 0, pageCount = { images.size })
                                val coroutineScope = rememberCoroutineScope()
                                HorizontalPager(
                                    state = pagerState1
                                ) { image ->
                                    Box(
                                        modifier = Modifier.fillMaxWidth()
                                    ){
                                        SubcomposeAsyncImage(
                                            model = images[image],
                                            loading = {
                                                Box(modifier = Modifier.fillMaxSize()) {
                                                    CircularProgressIndicator(
                                                        modifier = Modifier.align(
                                                            Alignment.Center
                                                        )
                                                    )
                                                }
                                            },
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(300.dp),
                                            contentDescription = "Game Images"
                                        )
                                        Box(
                                            modifier = Modifier
                                                .padding(10.dp)
                                                .width(45.dp)
                                                .align(Alignment.TopEnd)
                                                .height(25.dp)
                                                .clip(RoundedCornerShape(15.dp))
                                                .background(Color.White)

                                        ){
                                            Text(
                                                modifier = Modifier
                                                    .align(Alignment.Center)
                                                    .padding(horizontal = 3.dp),
                                                textAlign = TextAlign.Start,
                                                fontSize = 17.sp,
                                                text = "${image + 1}/${images.size}"
                                            )
                                        }
                                    }
                                }
                            }
                        }

                    }
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black)
                                .padding(5.dp)
                        ){
                            Text(
                                text = "Name :" + game.name_original,
                                modifier = Modifier.fillMaxWidth(),
                                color = Color.White,
                                textAlign = TextAlign.Start,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                text = "Date Released :" + game.released,
                                modifier = Modifier.fillMaxWidth(),
                                color = Color.White,
                                textAlign = TextAlign.Start,
                            )
                            Text(
                                text = "Requirements : " ,
                                modifier = Modifier.fillMaxWidth(),
                                color = Color.White,
                                textAlign = TextAlign.Start,
                                textDecoration = TextDecoration.Underline,
                                fontSize = 15.sp,
                            )
                            Text(
                                text = game.description_raw,
                                modifier = Modifier.fillMaxWidth(),
                                color = Color.White,
                                textAlign = TextAlign.Start,
                            )
                            Text(
                                text = "Supported Platforms : " ,
                                modifier = Modifier.fillMaxWidth(),
                                color = Color.White,
                                textAlign = TextAlign.Start,
                                textDecoration = TextDecoration.Underline,
                                fontSize = 15.sp,
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 5.dp)
                            ) {
                                items(items = game.platforms) { platform ->
                                    GamePlatformCard( platform = platform)
                                    Spacer(modifier = Modifier.width(10.dp))

                                }
                            }

                        }
                    }
                }
            }
        }
    }
}