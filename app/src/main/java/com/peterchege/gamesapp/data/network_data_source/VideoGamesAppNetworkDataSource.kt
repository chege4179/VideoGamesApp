package com.peterchege.gamesapp.data.network_data_source

import com.peterchege.gamesapp.data.api.RawgApi
import com.peterchege.gamesapp.data.api.responses.game_models.GetGamesResponse
import com.peterchege.gamesapp.data.api.responses.game_models.NetworkGame
import com.peterchege.gamesapp.data.api.responses.platform_models.NetworkPlatform
import com.peterchege.gamesapp.data.api.responses.platform_models.asExternalModel
import com.peterchege.gamesapp.data.api.responses.search_game_models.SearchGameResponse
import com.peterchege.gamesapp.data.api.responses.single_game_model.SingleGame
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

    suspend fun getGamesNetwork(pageSize:Int,page :Int):GetGamesResponse{
        return api.getGames(pageSize = pageSize,page = page)

    }

    suspend fun getGameById(id:String):SingleGame{
        return api.getGameById(id = id)
    }

    suspend fun searchGames(pageSize:Int,page :Int,searchTerm:String):SearchGameResponse{
        return api.searchGames(pageSize = pageSize, page = page, searchTerm = searchTerm)
    }

}