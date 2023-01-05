package com.peterchege.gamesapp.data.api.responses.single_game_model

data class MetacriticPlatform(
    val metascore: Int,
    val platform: Platform,
    val url: String
)