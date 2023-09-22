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
package com.peterchege.gamesapp.core.di

import com.peterchege.gamesapp.core.api.RawgApi
import com.peterchege.gamesapp.core.room.database.VideoGamesAppDatabase
import com.peterchege.gamesapp.data.local.PlatformLocalDataSource
import com.peterchege.gamesapp.data.local.PlatformLocalDataSourceImpl
import com.peterchege.gamesapp.data.remote.GamesNetworkDataSource
import com.peterchege.gamesapp.data.remote.GamesNetworkDataSourceImpl
import com.peterchege.gamesapp.data.remote.PlatformNetworkDataSource
import com.peterchege.gamesapp.data.remote.PlatformNetworkDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideLocalPlatformDataSource(db:VideoGamesAppDatabase):PlatformLocalDataSource{
        return PlatformLocalDataSourceImpl(db = db)
    }

    @Singleton
    @Provides
    fun provideRemotePlatformDataSource(api:RawgApi):PlatformNetworkDataSource{
        return PlatformNetworkDataSourceImpl(api = api)
    }
    @Singleton
    @Provides
    fun provideRemoteGamesDataSource(api:RawgApi):GamesNetworkDataSource{
        return GamesNetworkDataSourceImpl(api = api)
    }


}