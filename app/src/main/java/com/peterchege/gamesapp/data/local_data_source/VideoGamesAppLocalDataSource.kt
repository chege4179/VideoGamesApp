package com.peterchege.gamesapp.data.local_data_source

import com.peterchege.gamesapp.data.room.database.VideoGamesAppDatabase
import com.peterchege.gamesapp.data.room.entities.asDatabaseEntity
import com.peterchege.gamesapp.data.room.entities.asExternalModel
import com.peterchege.gamesapp.domain.models.Platform
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class VideoGamesAppLocalDataSource @Inject constructor(
    private val db:VideoGamesAppDatabase
) {
    // platforms
    suspend fun getPlatformsFromDB(): List<Platform> {
        return db.platformDao.getPlatformsFromDB().map {
             it.asExternalModel()
        }
    }
    suspend fun insertPlatformToDB(platform:Platform){
        return db.platformDao.insertPlatform(platform = platform.asDatabaseEntity())
    }
    suspend fun bulkInsertPlatforms(platforms:List<Platform>){
        platforms.map {
            insertPlatformToDB(platform = it)
        }
    }
    suspend fun clearPlatfromsDB(){
        return db.platformDao.deleteAllPlatforms()
    }
    suspend fun refreshLocalDB(platforms:List<Platform>){
        val localPlatforms = getPlatformsFromDB()
        if (localPlatforms.isNotEmpty()){
            clearPlatfromsDB()
            bulkInsertPlatforms(platforms = platforms)
        }else{
            bulkInsertPlatforms(platforms = platforms)
        }
    }
}