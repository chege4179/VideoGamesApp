package com.peterchege.gamesapp.domain.repository

import com.peterchege.gamesapp.data.api.responses.game_models.GetGamesResponse
import com.peterchege.gamesapp.data.api.responses.search_game_models.SearchGame
import com.peterchege.gamesapp.data.api.responses.search_game_models.SearchGameResponse
import com.peterchege.gamesapp.data.api.responses.single_game_model.SingleGame

interface GameRepository {
    suspend fun getGamesStream(pageSize:Int,page:Int): GetGamesResponse

    suspend fun getGameById(id:String): SingleGame

    suspend fun searchGames(pageSize:Int,page:Int,searchTerm:String):SearchGameResponse
}

