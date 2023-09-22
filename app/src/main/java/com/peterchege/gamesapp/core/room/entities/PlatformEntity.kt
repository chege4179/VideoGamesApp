/*
 * Copyright 2023 Games App
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.peterchege.gamesapp.core.room.entities

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

fun Platform.asDatabaseEntity(): PlatformEntity {
    return PlatformEntity(
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