package com.peterchege.gamesapp.data.api.responses.game_models

data class PlatformX(
    val platform: PlatformXX,
    val released_at: String?,
    val requirements_en: RequirementsEn?,
    val requirements_ru: RequirementsRu?
)