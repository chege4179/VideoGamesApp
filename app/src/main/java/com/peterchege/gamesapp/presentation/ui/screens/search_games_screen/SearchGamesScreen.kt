package com.peterchege.gamesapp.presentation.ui.screens.search_games_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.paging.compose.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
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
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.peterchege.gamesapp.presentation.ui.components.GamesCard
import com.peterchege.gamesapp.presentation.ui.components.SearchGameItem
import com.peterchege.gamesapp.presentation.ui.theme.DarkBlue
import com.peterchege.gamesapp.presentation.ui.theme.MainWhiteColor
import com.peterchege.gamesapp.util.Screens
import kotlinx.coroutines.delay

@Preview
@Composable
fun SearchGameScreenPreview() {
    SearchGamesScreen(navController = rememberNavController())
}
fun LazyListState.isScrolledToEnd() = layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
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
    LaunchedEffect(key1 = viewModel.searchTerm.value){
        Log.e("Change",viewModel.searchTerm.value)
        delay(500L)
        viewModel.searchGame()
    }

    LaunchedEffect(key1 = endOfListReached){
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
                        textColor = Color.White,
                        disabledTextColor = MainWhiteColor,
                        backgroundColor = MainWhiteColor,
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
    ) {
        if (viewModel.searchGameResults.value.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
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
                    .padding(10.dp)
            ) {
                items(items =  viewModel.searchGameResults.value) {
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