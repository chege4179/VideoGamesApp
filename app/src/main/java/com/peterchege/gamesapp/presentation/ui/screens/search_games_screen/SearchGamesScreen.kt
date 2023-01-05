package com.peterchege.gamesapp.presentation.ui.screens.search_games_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
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
import com.peterchege.gamesapp.presentation.ui.theme.DarkBlue
import com.peterchege.gamesapp.presentation.ui.theme.MainWhiteColor

@Preview
@Composable
fun SearchGameScreenPreview(){
    SearchGamesScreen(navController = rememberNavController())
}
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchGamesScreen(
    navController: NavController,
    viewModel:SearchGamesScreenViewModel = hiltViewModel()

) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Spacer(modifier = Modifier.height(2.dp))
            Row(
                Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = viewModel.searchTerm.value,
                    onValueChange = {

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
        Column(
            modifier = Modifier.fillMaxSize().background(Color.Black)
        ){


        }


    }


}