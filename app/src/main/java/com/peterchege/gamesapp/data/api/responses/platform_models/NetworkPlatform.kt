package com.peterchege.gamesapp.data.api.responses.platform_models

import com.peterchege.gamesapp.domain.models.Platform

data class NetworkPlatform(
    val games: List<PlatformGame>,
    val games_count: Int,
    val id: Int,
    val image: String?,
    val image_background: String,
    val name: String,
    val slug: String,
    val year_end: Int?,
    val year_start: Int?
)


fun NetworkPlatform.asExternalModel():Platform{
    return Platform(
        id = id,
        games_count = games_count,
        image_background = image_background,
        name = name,
        slug = slug,
        year_end = year_end,
        year_start = year_start,
        image = image,
    )
}