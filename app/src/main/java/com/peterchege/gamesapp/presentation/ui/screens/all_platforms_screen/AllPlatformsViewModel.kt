package com.peterchege.gamesapp.presentation.ui.screens.all_platforms_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.peterchege.gamesapp.data.repositoryImpl.OfflineFirstPlatformsRepository
import com.peterchege.gamesapp.domain.models.Platform
import com.peterchege.gamesapp.presentation.pagination.PlatformsPagination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class AllPlatformsViewModel @Inject constructor(
    private val platformsRepository: OfflineFirstPlatformsRepository,


    ) : ViewModel() {

    val platformsPager = Pager(PagingConfig(pageSize = 20)) {
        PlatformsPagination(repository = platformsRepository)
    }.flow.cachedIn(viewModelScope)

}