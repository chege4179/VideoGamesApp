package com.peterchege.gamesapp.data.repositoryImpl

import androidx.compose.runtime.collectAsState
import com.peterchege.gamesapp.data.local_data_source.VideoGamesAppLocalDataSource
import com.peterchege.gamesapp.data.network_data_source.VideoGamesAppNetworkDataSource
import com.peterchege.gamesapp.domain.models.Platform
import com.peterchege.gamesapp.domain.repository.PlatformsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineFirstPlatformsRepository @Inject constructor(
    private val localDataSource: VideoGamesAppLocalDataSource,
    private val networkDataSource: VideoGamesAppNetworkDataSource,

): PlatformsRepository {
    override suspend fun getPlatformsStream(pageSize:Int, page:Int): List<Platform> {
        val localPlatforms = localDataSource.getPlatformsFromDB()
        return if (localPlatforms.isEmpty()){
            localDataSource.bulkInsertPlatforms(networkDataSource.getPlatforms(pageSize = pageSize,page = page))
            networkDataSource.getPlatforms(pageSize = pageSize,page = page)

        }else{
            localDataSource.getPlatformsFromDB()
        }
    }
}