package com.peterchege.gamesapp.data.network_data_source

import com.peterchege.gamesapp.data.api.RawgApi
import com.peterchege.gamesapp.data.api.responses.platform_models.NetworkPlatform
import com.peterchege.gamesapp.data.api.responses.platform_models.asExternalModel
import com.peterchege.gamesapp.domain.models.Platform
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class VideoGamesAppNetworkDataSource @Inject constructor(
    private val api: RawgApi,
) {
    suspend fun getPlatforms(pageSize:Int,page :Int): List<Platform> {
        return api.getPlatforms(pageSize = pageSize,page = page).results
            .map { it.asExternalModel() }
    }

}