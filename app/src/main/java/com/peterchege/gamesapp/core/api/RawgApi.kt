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
package com.peterchege.gamesapp.core.api

import com.peterchege.gamesapp.core.api.responses.game_models.GetGamesResponse
import com.peterchege.gamesapp.core.api.responses.platform_models.GetPlatformResponse
import com.peterchege.gamesapp.core.api.responses.search_game_models.SearchGameResponse
import com.peterchege.gamesapp.core.api.responses.single_game_model.SingleGame
import com.peterchege.gamesapp.core.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RawgApi {

    @GET(value = "games")
    suspend fun getGames(
        @Query("key") apiKey:String = Constants.API_KEY,
        @Query("page_size") pageSize:Int,
        @Query("page") page : Int
    ):Response<GetGamesResponse>


    @GET(value = "platforms")
    suspend fun getPlatforms(
        @Query("key") apiKey:String = Constants.API_KEY,
        @Query("page_size") pageSize:Int,
        @Query("page") page : Int
    ):Response<GetPlatformResponse>

    @GET(value = "games/{id}")
    suspend fun getGameById(
        @Path("id") id:String,
        @Query("key") apiKey:String = Constants.API_KEY
    ): Response<SingleGame>


    @GET(value = "games")
    suspend fun searchGames(
        @Query("key") apiKey:String = Constants.API_KEY,
        @Query("page_size") pageSize:Int,
        @Query("page") page : Int,
        @Query("search") searchTerm :String,
    ): Response<SearchGameResponse>
}