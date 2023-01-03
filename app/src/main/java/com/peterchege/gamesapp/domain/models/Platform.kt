package com.peterchege.gamesapp.domain.models

data class Platform(
    val id: Int,
    val name: String,
    val slug: String,
    val games_count: Int,
    val image_background:String,
    val image:String?,
    val year_start:Int?,
    val year_end:Int?
)
