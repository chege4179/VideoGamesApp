package com.peterchege.gamesapp.domain.repository

import com.peterchege.gamesapp.domain.models.game_models.GetGamesResponse

interface GameRepository {
    suspend fun getGames(page_size:Int,page:Int): GetGamesResponse
}

