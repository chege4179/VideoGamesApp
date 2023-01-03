package com.peterchege.gamesapp.data.api.responses.platform_models

data class GetPlatformResponse(
    val count: Int,
    val next: String,
    val previous: Any?,
    val results: List<NetworkPlatform>
)