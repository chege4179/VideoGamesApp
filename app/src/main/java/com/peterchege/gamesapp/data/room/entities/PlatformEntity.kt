package com.peterchege.gamesapp.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.peterchege.gamesapp.domain.models.Platform

@Entity(tableName = "platform")
data class PlatformEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val slug: String,
    val games_count: Int,
    val image_background:String,
    val image:String?,
    val year_start:Int?,
    val year_end:Int?
)

fun PlatformEntity.asExternalModel(): Platform {
    return Platform(
        id = id,
        name = name,
        slug = slug,
        games_count = games_count,
        image_background = image_background,
        image = image,
        year_start = year_start,
        year_end = year_end,
    )
}