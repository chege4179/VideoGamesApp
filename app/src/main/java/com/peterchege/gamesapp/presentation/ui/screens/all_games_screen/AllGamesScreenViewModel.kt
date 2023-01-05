package com.peterchege.gamesapp.presentation.ui.screens.all_games_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.peterchege.gamesapp.data.repositoryImpl.OfflineFirstGamesRepository
import com.peterchege.gamesapp.presentation.pagination.GamesPagination
import com.peterchege.gamesapp.presentation.pagination.PlatformsPagination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AllGamesScreenViewModel @Inject constructor(
    private val repository: OfflineFirstGamesRepository,

) :ViewModel(){
    val gamesPager = Pager(PagingConfig(pageSize = 20)) {
        GamesPagination(repository = repository)
    }.flow.cachedIn(viewModelScope)



}