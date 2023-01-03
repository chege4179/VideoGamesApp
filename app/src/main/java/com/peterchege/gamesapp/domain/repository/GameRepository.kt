package com.peterchege.gamesapp.domain.repository

import com.peterchege.gamesapp.data.api.responses.game_models.GetGamesResponse

interface GameRepository {
    suspend fun getGames(page_size:Int,page:Int): GetGamesResponse
}

