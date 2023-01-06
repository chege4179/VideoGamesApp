package com.peterchege.gamesapp.presentation.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.peterchege.gamesapp.data.api.responses.game_models.NetworkGame
import com.peterchege.gamesapp.data.api.responses.search_game_models.SearchGame
import com.peterchege.gamesapp.data.repositoryImpl.OfflineFirstGamesRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class SearchGamesPagination @Inject constructor(
    private val repository: OfflineFirstGamesRepository,
    private val searchTerm: String,

    ) : PagingSource<Int, SearchGame>() {


    override fun getRefreshKey(state: PagingState<Int, SearchGame>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchGame> {
        delay(5000L)
        return try {
            val nextPageNumber = params.key ?: 1
            val response = repository.searchGames(
                pageSize = 10,
                page = nextPageNumber,
                searchTerm = searchTerm
            )
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