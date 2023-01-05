package com.peterchege.gamesapp.data.api

import com.peterchege.gamesapp.data.api.responses.game_models.GetGamesResponse
import com.peterchege.gamesapp.data.api.responses.platform_models.GetPlatformResponse
import com.peterchege.gamesapp.data.api.responses.single_game_model.SingleGame
import com.peterchege.gamesapp.util.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RawgApi {

    @GET(value = "games")
    suspend fun getGames(
        @Query("key") apiKey:String = Constants.API_KEY,
        @Query("page_size") pageSize:Int,
        @Query("page") page : Int
    ): GetGamesResponse


    @GET(value = "platforms")
    suspend fun getPlatforms(
        @Query("key") apiKey:String = Constants.API_KEY,
        @Query("page_size") pageSize:Int,
        @Query("page") page : Int
    ): GetPlatformResponse


    @GET(value = "games/{id}")
    suspend fun getGameById(
        @Path("id") id:String,
        @Query("key") apiKey:String = Constants.API_KEY
    ):SingleGame
}