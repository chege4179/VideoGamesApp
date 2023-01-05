package com.peterchege.gamesapp.presentation.ui.screens.search_games_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SearchGamesScreenViewModel @Inject constructor(

) :ViewModel(){


    private val _searchTerm = mutableStateOf<String>("")
    val searchTerm : State<String> = _searchTerm
}