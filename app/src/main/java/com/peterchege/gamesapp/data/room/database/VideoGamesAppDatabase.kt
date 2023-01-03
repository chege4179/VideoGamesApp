package com.peterchege.gamesapp.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.peterchege.gamesapp.data.room.dao.PlatformDao
import com.peterchege.gamesapp.data.room.entities.PlatformEntity

@Database(
    entities = [PlatformEntity::class],
    version = 1
)
abstract class VideoGamesAppDatabase: RoomDatabase() {

    abstract val platformDao: PlatformDao

}