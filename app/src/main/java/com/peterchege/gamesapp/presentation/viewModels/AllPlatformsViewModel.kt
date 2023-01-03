package com.peterchege.gamesapp.presentation.viewModels

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
    private val _platforms = mutableStateOf<List<Platform>>(emptyList())
    val platforms: State<List<Platform>> = _platforms

    private val _isError = mutableStateOf(false)
    val isError: State<Boolean> = _isError

    private val _errorMsg = mutableStateOf("")
    val errorMsg: State<String> = _errorMsg

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _msg = mutableStateOf("")
    val msg: State<String> = _msg

    val platformsPager = Pager(PagingConfig(pageSize = 20)) {
        PlatformsPagination(repository = platformsRepository)
    }.flow.cachedIn(viewModelScope)


//    init {
//        getPlatforms()
//    }
//
//    private fun getPlatforms(){
//        _isLoading.value = true
//        Log.e("We here","We here 1")
//        viewModelScope.launch {
//            try {
//                val response = platformsRepository.getPlatformsStream(pageSize = 20, page = 1)
//                _isLoading.value = false
//                _isError.value = false
//                _platforms.value = response
//                Log.e("We here","We here 2 ")
//
//            }catch (e: HttpException){
//                Log.e("HTTP ERROR",e.localizedMessage ?: "Http error")
//                _isLoading.value = false
//                _isError.value = true
//                _errorMsg.value = e.localizedMessage?: "An unexpected error occurred"
//                Log.e("We here","We here 3")
//            }catch (e: IOException){
//                Log.e("IO ERROR",e.toString() )
//                _isLoading.value = false
//                _isError.value = true
//                _errorMsg.value = "Please check your internet connection"
//                Log.e("We here","We here 4")
//            }
//
//        }
//
//    }


}