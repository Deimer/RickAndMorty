package com.testvass.rickandmorty.di

import com.testvass.repository.repositories.character.ICharacterRepository
import com.testvass.usecase.characters.FetchCharacterByIdUseCase
import com.testvass.usecase.characters.FetchCharacterByNameUseCase
import com.testvass.usecase.characters.FetchCharactersUseCase
import com.testvass.usecase.characters.UpdateCharacterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object UseCaseModule {

    @Provides
    @ActivityRetainedScoped
    fun provideFetchCharactersUseCase(
        characterRepository: ICharacterRepository
    ) = FetchCharactersUseCase(characterRepository)

    @Provides
    @ActivityRetainedScoped
    fun provideFetchCharacterByIdUseCase(
        characterRepository: ICharacterRepository
    ) = FetchCharacterByIdUseCase(characterRepository)

    @Provides
    @ActivityRetainedScoped
    fun provideFetchCharacterByNameUseCase(
        characterRepository: ICharacterRepository
    ) = FetchCharacterByNameUseCase(characterRepository)

    @Provides
    @ActivityRetainedScoped
    fun provideUpdateCharacterUseCase(
        characterRepository: ICharacterRepository
    ) = UpdateCharacterUseCase(characterRepository)
}