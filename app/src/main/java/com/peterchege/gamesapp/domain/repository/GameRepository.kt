package com.peterchege.gamesapp.domain.repository

import com.peterchege.gamesapp.data.api.responses.game_models.GetGamesResponse
import com.peterchege.gamesapp.data.api.responses.single_game_model.SingleGame

interface GameRepository {
    suspend fun getGamesStream(pageSize:Int,page:Int): GetGamesResponse

    suspend fun getGameById(id:String): SingleGame
}

