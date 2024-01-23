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
package com.peterchege.gamesapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.peterchege.gamesapp.core.api.responses.search_game_models.SearchGame
import com.peterchege.gamesapp.presentation.theme.GrayColor

@Composable
fun SearchGameItem(
    modifier: Modifier = Modifier,
    game: SearchGame,
    onNavigateToGameScreen: (String) -> Unit,
    onAddToFavorite: (SearchGame) -> Unit,
) {
    Card(
        modifier = modifier
            .clickable {
                onNavigateToGameScreen(game.id.toString())

            },
        shape = RoundedCornerShape(15),
    ) {
        Row(
            modifier = modifier
                .background(GrayColor)
        ) {
            SubcomposeAsyncImage(
                model = game.background_image,
                loading = {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                },
                contentDescription = "Item Image",
                modifier = Modifier
                    .weight(1f)
                    .height(105.dp)
                    .clip(RoundedCornerShape(topStart =  15.dp, bottomStart = 15.dp) )
                ,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(5.dp)
            ) {
                Text(
                    text = game.name,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .padding(horizontal = 10.dp)

                    ,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = game.released,
                        color = Color.Black,
                        fontSize = 22.sp,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Light
                    )
                }

            }
        }
    }
}
