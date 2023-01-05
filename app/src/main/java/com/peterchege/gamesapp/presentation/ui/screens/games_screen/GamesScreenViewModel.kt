package com.peterchege.gamesapp.presentation.ui.screens.games_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peterchege.gamesapp.data.api.responses.single_game_model.SingleGame
import com.peterchege.gamesapp.data.repositoryImpl.OfflineFirstGamesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
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
            try {
                _isLoading.value = true
                val response = repository.getGameById(id)
                _isLoading.value = false
                _game.value = response
            }catch (e: HttpException){
                _isLoading.value = false
                _isError.value = true
                _errorMsg.value = "Server down ... Please try again"
            }catch (e: IOException){
                _isLoading.value = false
                _isError.value = true
                _errorMsg.value = "Please check your internet connection"
            }
        }
    }

}