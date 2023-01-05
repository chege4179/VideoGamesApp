package com.peterchege.gamesapp.presentation.ui.screens.games_screen

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.peterchege.gamesapp.util.Screens
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class)
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
                    viewModel.game.value?.let {
                        Text(
                            text = it.name_original,
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
                    if (game.background_image == null || game.background_image_additional == null){
                        item{
                            Text(text = "No Image available")
                        }

                    }else{
                        val images = listOf<String>(game.background_image,game.background_image_additional)
                        item {
                            val pagerState1 = rememberPagerState(initialPage = 0)
                            val coroutineScope = rememberCoroutineScope()
                            HorizontalPager(
                                count = images.size,
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
            }
        }
    }
}