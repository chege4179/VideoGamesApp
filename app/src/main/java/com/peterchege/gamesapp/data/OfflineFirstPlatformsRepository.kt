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
package com.peterchege.gamesapp.data

import com.peterchege.gamesapp.core.api.responses.platform_models.GetPlatformResponse
import com.peterchege.gamesapp.core.util.NetworkResult
import com.peterchege.gamesapp.data.local.PlatformLocalDataSource
import com.peterchege.gamesapp.data.remote.PlatformNetworkDataSource
import com.peterchege.gamesapp.domain.repository.PlatformsRepository
import javax.inject.Inject

class OfflineFirstPlatformsRepository @Inject constructor(
    private val localDataSource: PlatformLocalDataSource,
    private val networkDataSource: PlatformNetworkDataSource,

    ): PlatformsRepository {
    override suspend fun getPlatforms(pageSize:Int, page:Int): NetworkResult<GetPlatformResponse> {
        return networkDataSource.getPlatforms(pageSize = pageSize,page = page)
    }
}