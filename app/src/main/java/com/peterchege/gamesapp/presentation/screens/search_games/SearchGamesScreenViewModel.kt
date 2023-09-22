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

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peterchege.gamesapp.core.api.responses.search_game_models.SearchGame
import com.peterchege.gamesapp.core.util.NetworkResult
import com.peterchege.gamesapp.data.OfflineFirstGamesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchGamesScreenViewModel @Inject constructor(
    private val repository: OfflineFirstGamesRepository,

    ) : ViewModel() {


    private val _searchTerm = mutableStateOf<String>("")
    val searchTerm: State<String> = _searchTerm

    private val _searchPage = mutableStateOf<Int>(1)
    val searchPage: State<Int> = _searchPage

    private val _searchGameResults = mutableStateOf<List<SearchGame>>(emptyList())
    val searchGameResults: State<List<SearchGame>> = _searchGameResults


    fun onChangeSearchTerm(text: String) {
        _searchTerm.value = text

    }
    fun updateSearchPage(){
        _searchPage.value += 1
    }


    fun searchGame() {
        viewModelScope.launch {
            val response = repository.searchGames(
                pageSize = 10,
                page = searchPage.value,
                searchTerm = searchTerm.value
            )
            when(response){
                is NetworkResult.Success -> {
                    _searchGameResults.value = response.data.results
                }
                is NetworkResult.Error -> {

                }
                is NetworkResult.Exception -> {

                }
            }

        }

    }
}