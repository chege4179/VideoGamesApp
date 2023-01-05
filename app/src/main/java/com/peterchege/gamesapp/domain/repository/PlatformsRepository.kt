package com.peterchege.gamesapp.domain.repository

import com.peterchege.gamesapp.data.api.responses.single_game_model.SingleGame
import com.peterchege.gamesapp.domain.models.Platform
import kotlinx.coroutines.flow.Flow

interface PlatformsRepository {
    suspend fun getPlatformsStream(pageSize:Int,page:Int):List<Platform>


}