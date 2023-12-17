package com.testvass.usecase.characters

import com.testvass.repository.repositories.character.ICharacterRepository
import javax.inject.Inject

class FetchCharacterByIdUseCase @Inject constructor(
    private val characterRepository: ICharacterRepository
) {

    suspend fun invoke(characterId: Int) =
        characterRepository.fetchById(characterId)
}