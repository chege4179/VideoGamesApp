package com.peterchege.gamesapp.domain.models.platform_models

data class GetPlatformResponse(
    val count: Int,
    val next: String,
    val previous: Any?,
    val results: List<Result>
)