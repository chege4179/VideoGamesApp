package com.peterchege.gamesapp.data.repositoryImpl

import com.peterchege.gamesapp.data.api.RawgApi
import com.peterchege.gamesapp.data.api.responses.game_models.GetGamesResponse
import com.peterchege.gamesapp.domain.repository.GameRepository
import javax.inject.Inject

class OfflineFirstGamesRepository @Inject constructor(
    private val api:RawgApi
) :GameRepository{

    override suspend fun getGamesStream(pageSize:Int,page:Int): GetGamesResponse {
        return api.getGames(pageSize = pageSize, page = page)
    }
}