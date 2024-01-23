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
package com.peterchege.gamesapp.presentation.screens.search_games

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.peterchege.gamesapp.core.util.Screens
import com.peterchege.gamesapp.presentation.components.SearchGameItem
import com.peterchege.gamesapp.presentation.theme.DarkBlue
import com.peterchege.gamesapp.presentation.theme.MainWhiteColor
import kotlinx.coroutines.delay

@Preview
@Composable
fun SearchGameScreenPreview() {
    SearchGamesScreen(navController = rememberNavController())
}

fun LazyListState.isScrolledToEnd() =
    layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchGamesScreen(
    navController: NavController,
    viewModel: SearchGamesScreenViewModel = hiltViewModel()

) {

    val scrollState = rememberLazyListState()
    val endOfListReached by remember {
        derivedStateOf {
            scrollState.isScrolledToEnd()
        }
    }
    LaunchedEffect(key1 = viewModel.searchTerm.value) {
        Log.e("Change", viewModel.searchTerm.value)
        delay(500L)
        viewModel.searchGame()
    }

    LaunchedEffect(key1 = endOfListReached) {
        viewModel.updateSearchPage()
        viewModel.searchGame()

    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Spacer(modifier = Modifier.height(2.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(Color.Black),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = viewModel.searchTerm.value,
                    onValueChange = {
                        viewModel.onChangeSearchTerm(it)

                    },
                    placeholder = {
                        Text(
                            text = "Search",
                        )
                    },

                    modifier = Modifier
                        .fillMaxWidth(0.80f)
                        .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                        .clickable {

                        },
                    shape = RoundedCornerShape(size = 8.dp),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        autoCorrect = true,
                        keyboardType = KeyboardType.Text,
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        disabledTextColor = MainWhiteColor,
                        focusedIndicatorColor = MainWhiteColor,
                        unfocusedIndicatorColor = MainWhiteColor,
                        disabledIndicatorColor = MainWhiteColor
                    ),
                    textStyle = TextStyle(color = Color.Black),
                    maxLines = 1,
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Game",
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {

                                }
                        )
                    }
                )

                IconButton(
                    onClick = {

                    }) {

                    Icon(
                        modifier = Modifier
                            .size(55.dp)
                            .clip(
                                shape = RoundedCornerShape(
                                    size = 8.dp
                                )
                            )
                            .background(MainWhiteColor)
                            .padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 4.dp),
                        imageVector = Icons.Default.FilterList,
                        contentDescription = null,
                        tint = DarkBlue
                    )
                }
            }
        },
    ) { paddingValues ->
        if (viewModel.searchGameResults.value.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "No games results found")
            }
        } else {
            LazyColumn(
                state = scrollState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(10.dp)
            ) {
                items(items = viewModel.searchGameResults.value) {
                    SearchGameItem(
                        game = it,
                        onNavigateToGameScreen = {
                            navController.navigate(Screens.GAME_SCREEN + "/${it}")

                        },
                        onAddToFavorite = {

                        }
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }

    }


}