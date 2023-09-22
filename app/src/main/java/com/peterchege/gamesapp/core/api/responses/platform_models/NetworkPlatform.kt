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
package com.peterchege.gamesapp.core.api.responses.platform_models

import com.peterchege.gamesapp.domain.models.Platform
import kotlinx.serialization.Serializable

@Serializable
data class NetworkPlatform(
    val games: List<com.peterchege.gamesapp.core.api.responses.platform_models.PlatformGame>,
    val games_count: Int,
    val id: Int,
    val image: String?,
    val image_background: String,
    val name: String,
    val slug: String,
    val year_end: Int?,
    val year_start: Int?
)


fun com.peterchege.gamesapp.core.api.responses.platform_models.NetworkPlatform.asExternalModel():Platform{
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