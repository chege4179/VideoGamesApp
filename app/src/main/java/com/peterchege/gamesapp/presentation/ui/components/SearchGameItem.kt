package com.peterchege.gamesapp.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
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
import com.peterchege.gamesapp.data.api.responses.search_game_models.SearchGame
import com.peterchege.gamesapp.presentation.ui.theme.GrayColor

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
        elevation = 3.dp
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
