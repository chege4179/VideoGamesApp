package com.peterchege.gamesapp.presentation.pagination

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.peterchege.gamesapp.data.api.responses.game_models.NetworkGame
import com.peterchege.gamesapp.data.repositoryImpl.OfflineFirstGamesRepository
import com.peterchege.gamesapp.domain.models.Platform
import kotlinx.coroutines.delay
import javax.inject.Inject

class GamesPagination @Inject constructor (
    private val repository: OfflineFirstGamesRepository,

    ): PagingSource<Int, NetworkGame>() {


    override fun getRefreshKey(state: PagingState<Int, NetworkGame>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NetworkGame> {
        delay(5000L)
        return try {
            val nextPageNumber = params.key ?: 1
            val response = repository.getGamesStream(pageSize = 30, page = nextPageNumber)

            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = if (response.results.isNotEmpty()) nextPageNumber + 1 else null
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}