package com.peterchege.gamesapp.data.api.responses.search_game_models

data class SearchGameResponse(
    val count: Int,
    val next: String,
    val previous: Any?,
    val results: List<SearchGame>,
    val user_platforms: Boolean
)