package com.peterchege.gamesapp.data.api.responses.search_game_models

data class Rating(
    val count: Int,
    val id: Int,
    val percent: Double,
    val title: String
)