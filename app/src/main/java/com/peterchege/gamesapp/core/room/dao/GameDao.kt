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

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.peterchege.gamesapp.core.api.responses.game_models.NetworkGame
import com.peterchege.gamesapp.core.room.entities.PlatformEntity

@Dao
interface GameDao {

    @Query(value = "SELECT * FROM game")
    suspend fun getGamesFromDB(): List<com.peterchege.gamesapp.core.api.responses.game_models.NetworkGame>

    @Query(value = "SELECT * FROM game WHERE id = :id")
    suspend fun getGameById(id: String): com.peterchege.gamesapp.core.api.responses.game_models.NetworkGame?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(platform: com.peterchege.gamesapp.core.api.responses.game_models.NetworkGame)

    @Query(value = "DELETE FROM game WHERE id = :id")
    suspend fun deleteGamesById(id: String)

    @Query(value = "DELETE FROM game")
    suspend fun deleteAllPlatforms()
}