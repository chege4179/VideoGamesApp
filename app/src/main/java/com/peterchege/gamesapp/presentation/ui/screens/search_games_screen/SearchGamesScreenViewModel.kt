package com.peterchege.gamesapp.presentation.ui.screens.search_games_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.peterchege.gamesapp.data.api.responses.search_game_models.SearchGame
import com.peterchege.gamesapp.data.repositoryImpl.OfflineFirstGamesRepository
import com.peterchege.gamesapp.presentation.pagination.GamesPagination
import com.peterchege.gamesapp.presentation.pagination.SearchGamesPagination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
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
            try {
                val response = repository.searchGames(
                    pageSize = 10,
                    page = searchPage.value,
                    searchTerm = searchTerm.value
                )
                if (searchPage.value ==1){
                    _searchGameResults.value = response.results
                }else if (searchPage.value > 1){
                    _searchGameResults.value += response.results
                }
            } catch (e: HttpException) {
                Log.e("Http error",e.localizedMessage?:"Error")

            } catch (e: IOException) {
                Log.e("Io error",e.localizedMessage?:"Error")
            }
        }

    }
}