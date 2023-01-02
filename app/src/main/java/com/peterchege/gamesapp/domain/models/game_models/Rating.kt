package com.peterchege.gamesapp.domain.models.game_models

data class Rating(
    val count: Int,
    val id: Int,
    val percent: Double,
    val title: String
)