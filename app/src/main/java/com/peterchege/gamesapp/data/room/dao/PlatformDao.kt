package com.peterchege.gamesapp.data.room.dao

import androidx.room.*
import com.peterchege.gamesapp.data.room.entities.PlatformEntity

import kotlinx.coroutines.flow.Flow

@Dao
interface PlatformDao {

    @Query("SELECT * FROM platform")
    suspend fun getPlatformsFromDB(): List<PlatformEntity>

    @Query("SELECT * FROM platform WHERE id = :id")
    suspend fun getPlatformById(id: String): PlatformEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlatform(platform: PlatformEntity)

    @Query("DELETE FROM platform WHERE id = :id")
    suspend fun deletePlatformById(id: String)

    @Query("DELETE FROM platform")
    suspend fun deleteAllPlatforms()
}