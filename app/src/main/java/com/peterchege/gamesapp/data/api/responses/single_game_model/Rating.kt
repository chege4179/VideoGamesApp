package com.peterchege.gamesapp.data.api.responses.single_game_model

data class Rating(
    val count: Int,
    val id: Int,
    val percent: Double,
    val title: String
)