package com.testvass.usecase.characters

import com.testvass.repository.repositories.character.ICharacterRepository
import javax.inject.Inject

class FetchCharacterByNameUseCase @Inject constructor(
    private val characterRepository: ICharacterRepository
) {

    suspend fun invoke(name: String) =
        characterRepository.fetchByName(name)
}