package com.testvass.usecase.characters

import com.testvass.repository.models.CharacterModel
import com.testvass.repository.repositories.character.ICharacterRepository
import javax.inject.Inject

class UpdateCharacterUseCase @Inject constructor(
    private val characterRepository: ICharacterRepository
) {

    suspend fun invoke(character: CharacterModel) =
        characterRepository.update(character)
}