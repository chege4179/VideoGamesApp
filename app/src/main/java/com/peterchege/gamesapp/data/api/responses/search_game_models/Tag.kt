package com.peterchege.gamesapp.data.api.responses.search_game_models

data class Tag(
    val games_count: Int,
    val id: Int,
    val image_background: String?,
    val language: String,
    val name: String,
    val slug: String
)