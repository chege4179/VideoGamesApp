package com.peterchege.gamesapp.data.api.responses.game_models

data class GetGamesResponse(
    val count: Int,
    val description: String,
    val filters: Filters,
    val next: String,
    val nofollow: Boolean,
    val nofollow_collections: List<String>,
    val noindex: Boolean,
    val previous: String?,
    val results: List<Result>,
    val seo_description: String,
    val seo_h1: String,
    val seo_keywords: String,
    val seo_title: String
)