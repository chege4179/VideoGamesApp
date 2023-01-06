package com.peterchege.gamesapp.data.repositoryImpl

import com.peterchege.gamesapp.data.api.RawgApi
import com.peterchege.gamesapp.data.api.responses.game_models.GetGamesResponse
import com.peterchege.gamesapp.data.api.responses.search_game_models.SearchGameResponse
import com.peterchege.gamesapp.data.api.responses.single_game_model.SingleGame
import com.peterchege.gamesapp.data.network_data_source.VideoGamesAppNetworkDataSource
import com.peterchege.gamesapp.domain.repository.GameRepository
import javax.inject.Inject

class OfflineFirstGamesRepository @Inject constructor(
    private val networkDataSource: VideoGamesAppNetworkDataSource,
) : GameRepository {

    override suspend fun getGamesStream(pageSize: Int, page: Int): GetGamesResponse {
        return networkDataSource.getGamesNetwork(pageSize = pageSize, page = page)
    }

    override suspend fun getGameById(id: String): SingleGame {
        return networkDataSource.getGameById(id = id)
    }

    override suspend fun searchGames(
        pageSize: Int,
        page: Int,
        searchTerm: String
    ): SearchGameResponse {
        return networkDataSource.searchGames(
            pageSize = pageSize,
            page = page,
            searchTerm = searchTerm
        )
    }
}