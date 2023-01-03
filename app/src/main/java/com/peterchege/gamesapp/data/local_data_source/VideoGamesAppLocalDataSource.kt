package com.peterchege.gamesapp.data.local_data_source

import com.peterchege.gamesapp.data.room.database.VideoGamesAppDatabase
import com.peterchege.gamesapp.data.room.entities.asExternalModel
import com.peterchege.gamesapp.domain.models.Platform
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class VideoGamesAppLocalDataSource @Inject constructor(
    private val db:VideoGamesAppDatabase
) {
    suspend fun getPlatforms(): List<Platform> {
        return db.platformDao.getPlatformsFromDB().map {
             it.asExternalModel()
        }
    }
}