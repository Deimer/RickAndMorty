package com.testvass.datasource.di

import com.testvass.datasource.local.character.CharacterLocalDataSource
import com.testvass.datasource.local.character.ICharacterLocalDataSource
import com.testvass.datasource.remote.character.CharacterRemoteDataSource
import com.testvass.datasource.remote.character.ICharacterRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindCharacterRemoteDataSource(
        implRemoteDataSource: CharacterRemoteDataSource
    ): ICharacterRemoteDataSource

    @Binds
    abstract fun bindCharacterLocalDataSource(
        implLocalDataSource: CharacterLocalDataSource
    ): ICharacterLocalDataSource
}