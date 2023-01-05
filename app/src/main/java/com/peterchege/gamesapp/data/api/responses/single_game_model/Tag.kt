package com.peterchege.gamesapp.data.api.responses.single_game_model

data class Tag(
    val games_count: Int,
    val id: Int,
    val image_background: String,
    val language: String,
    val name: String,
    val slug: String
)