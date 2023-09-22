/*
 * Copyright 2023 Games App
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.peterchege.gamesapp.data.remote

import com.peterchege.gamesapp.core.api.responses.game_models.GetGamesResponse
import com.peterchege.gamesapp.core.api.responses.search_game_models.SearchGameResponse
import com.peterchege.gamesapp.core.api.responses.single_game_model.SingleGame
import com.peterchege.gamesapp.core.util.NetworkResult

interface GamesNetworkDataSource {

    suspend fun getGames(pageSize:Int,page :Int):NetworkResult<GetGamesResponse>

    suspend fun getGameById(id:String):NetworkResult<SingleGame>

    suspend fun searchGames(pageSize:Int,page :Int,searchTerm:String):NetworkResult<SearchGameResponse>


}