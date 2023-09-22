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

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peterchege.gamesapp.core.api.responses.single_game_model.SingleGame
import com.peterchege.gamesapp.core.util.NetworkResult
import com.peterchege.gamesapp.data.OfflineFirstGamesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GamesScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: OfflineFirstGamesRepository,

    ) :ViewModel(){


    private val _errorMsg = mutableStateOf("")
    val errorMsg: State<String> =_errorMsg

    private val _isError = mutableStateOf(false)
    val isError: State<Boolean> =_isError

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> =_isLoading

    private val _game = mutableStateOf<SingleGame?>(null)
    val game : State<SingleGame?> = _game



    init {
        savedStateHandle.get<String>("id")?.let {
            getGameById(it)
        }
    }


    private fun getGameById(id:String){
        viewModelScope.launch {
            val response = repository.getGameById(id)
            when(response){
                is NetworkResult.Success -> {
                    _game.value = response.data
                }
                is NetworkResult.Error -> {

                }
                is NetworkResult.Exception -> {

                }
            }
        }
    }

}