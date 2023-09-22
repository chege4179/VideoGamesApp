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
package com.peterchege.gamesapp.domain.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.peterchege.gamesapp.core.api.responses.platform_models.NetworkPlatform
import com.peterchege.gamesapp.core.util.NetworkResult
import com.peterchege.gamesapp.data.OfflineFirstPlatformsRepository
import com.peterchege.gamesapp.domain.models.Platform
import kotlinx.coroutines.delay
import javax.inject.Inject

class PlatformsPagination @Inject constructor (
    private val repository: OfflineFirstPlatformsRepository,

    ): PagingSource<Int, NetworkPlatform>() {


    override fun getRefreshKey(state: PagingState<Int, NetworkPlatform>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NetworkPlatform> {
        delay(5000L)
        return try {
            val nextPageNumber = params.key ?: 1
            val response = repository.getPlatforms(pageSize = 10, page = nextPageNumber)
            when(response){
                is NetworkResult.Success -> {
                    LoadResult.Page(
                        data = response.data.results,
                        prevKey = null,
                        nextKey = if (response.data.results.isNotEmpty()) nextPageNumber + 1 else null
                    )
                }
                is NetworkResult.Error -> {
                    LoadResult.Error(Throwable(message = response.message))
                }
                is NetworkResult.Exception -> {
                    LoadResult.Error(Throwable(message = response.e.message))
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}

