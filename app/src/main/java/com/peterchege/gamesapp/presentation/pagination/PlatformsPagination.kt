package com.peterchege.gamesapp.presentation.pagination

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.peterchege.gamesapp.data.repositoryImpl.OfflineFirstPlatformsRepository
import com.peterchege.gamesapp.domain.models.Platform
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class PlatformsPagination @Inject constructor (
    private val repository: OfflineFirstPlatformsRepository,

    ): PagingSource<Int, Platform>() {


    override fun getRefreshKey(state: PagingState<Int, Platform>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Platform> {
        delay(5000L)
        return try {
            val nextPageNumber = params.key ?: 1
            val response = repository.getPlatformsStream(pageSize = 10, page = nextPageNumber)
            Log.e("Response",response.size.toString())
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = if (response.isNotEmpty()) nextPageNumber + 1 else null
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}

suspend fun <T> Flow<List<T>>.flattenToList() =
    flatMapConcat { it.asFlow() }.toList()