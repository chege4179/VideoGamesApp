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
package com.peterchege.gamesapp.core.room.dao

import androidx.room.*
import com.peterchege.gamesapp.core.room.entities.PlatformEntity

import kotlinx.coroutines.flow.Flow

@Dao
interface PlatformDao {

    @Query("SELECT * FROM platform")
    fun getPlatformsFromDB(): Flow<List<PlatformEntity>>

    @Query("SELECT * FROM platform WHERE id = :id")
    fun getPlatformById(id: String):Flow<PlatformEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlatforms(platforms:List<PlatformEntity>)

    @Query("DELETE FROM platform WHERE id = :id")
    suspend fun deletePlatformById(id: String)

    @Query("DELETE FROM platform")
    suspend fun deleteAllPlatforms()
}