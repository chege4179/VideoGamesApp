package com.peterchege.gamesapp.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.peterchege.gamesapp.data.api.responses.game_models.NetworkGame
import com.peterchege.gamesapp.data.room.entities.PlatformEntity

@Dao
interface GameDao {

    @Query(value = "SELECT * FROM game")
    suspend fun getGamesFromDB(): List<NetworkGame>

    @Query(value = "SELECT * FROM game WHERE id = :id")
    suspend fun getGameById(id: String): NetworkGame?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(platform: NetworkGame)

    @Query(value = "DELETE FROM game WHERE id = :id")
    suspend fun deleteGamesById(id: String)

    @Query(value = "DELETE FROM game")
    suspend fun deleteAllPlatforms()
}