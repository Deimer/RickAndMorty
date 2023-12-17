package com.testvass.repository.repositories.character

import com.testvass.repository.models.CharacterModel
import com.testvass.repository.utils.OnResult

interface ICharacterRepository {

    suspend fun fetch(): OnResult<List<CharacterModel>>

    suspend fun update(
        character: CharacterModel
    ): OnResult<Boolean>

    suspend fun delete(
        character: CharacterModel
    ): OnResult<Boolean>

    suspend fun fetchById(
        characterId: Int
    ): OnResult<CharacterModel>

    suspend fun fetchByName(
        characterId: Int
    ): OnResult<CharacterModel>
}