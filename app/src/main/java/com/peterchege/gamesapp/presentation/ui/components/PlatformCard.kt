package com.peterchege.gamesapp.presentation.ui.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MonitorHeart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.SubcomposeAsyncImage
import com.peterchege.gamesapp.domain.models.Platform

@ExperimentalCoilApi
@Composable
fun PlatformCard(
    platform: Platform,
    onNavigateToPlatformScreen:(String) -> Unit,


    ) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clickable {
                onNavigateToPlatformScreen(platform.id.toString())
            }
        ,

        ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SubcomposeAsyncImage(
                model = platform.image_background,
                loading = {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                },
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .height(150.dp),
                contentDescription = "Product Images"
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,

                ) {
                Column(
                    modifier = Modifier.fillMaxWidth(0.85f),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = platform.name)
                }

            }

        }
    }
}