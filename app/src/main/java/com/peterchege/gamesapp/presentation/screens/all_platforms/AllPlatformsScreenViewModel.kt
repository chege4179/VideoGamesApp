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
package com.peterchege.gamesapp.presentation.screens.all_platforms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.peterchege.gamesapp.data.OfflineFirstPlatformsRepository
import com.peterchege.gamesapp.domain.pagination.PlatformsPagination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AllPlatformsScreenViewModel @Inject constructor(
    private val platformsRepository: OfflineFirstPlatformsRepository,


    ) : ViewModel() {

    val platformsPager = Pager(PagingConfig(pageSize = 20)) {
        PlatformsPagination(repository = platformsRepository)
    }.flow.cachedIn(viewModelScope)

}