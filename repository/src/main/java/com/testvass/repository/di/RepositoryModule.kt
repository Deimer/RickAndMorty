package com.testvass.repository.di

import com.testvass.repository.repositories.character.CharacterRepository
import com.testvass.repository.repositories.character.ICharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCharacterRepository(
        implRepository: CharacterRepository
    ): ICharacterRepository
}