package com.peterchege.gamesapp.data.api.responses.game_models

data class AddedByStatus(
    val beaten: Int,
    val dropped: Int,
    val owned: Int,
    val playing: Int,
    val toplay: Int,
    val yet: Int
)