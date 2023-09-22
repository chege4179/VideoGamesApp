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
package com.peterchege.gamesapp.data.local

import com.peterchege.gamesapp.core.room.database.VideoGamesAppDatabase
import com.peterchege.gamesapp.core.room.entities.PlatformEntity
import com.peterchege.gamesapp.core.room.entities.asDatabaseEntity
import com.peterchege.gamesapp.core.room.entities.asExternalModel
import com.peterchege.gamesapp.domain.models.Platform
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlatformLocalDataSourceImpl @Inject constructor(
    private val db: VideoGamesAppDatabase
):PlatformLocalDataSource {
    // platforms
    override suspend fun cachePlatforms(platforms: List<Platform>) {
        return db.platformDao.insertPlatforms(platforms = platforms.map { it.asDatabaseEntity() })
    }

    override fun getAllPlatforms(): Flow<List<PlatformEntity>> {
        return db.platformDao.getPlatformsFromDB()
    }

    override suspend fun deletePlatforms() {
        db.platformDao.deleteAllPlatforms()
    }

    override fun getPlatformById(id:String): Flow<PlatformEntity?> {
        return db.platformDao.getPlatformById(id)
    }

}