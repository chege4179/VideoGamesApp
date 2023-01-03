package com.peterchege.gamesapp.data.api.responses.game_models

data class Rating(
    val count: Int,
    val id: Int,
    val percent: Double,
    val title: String
)