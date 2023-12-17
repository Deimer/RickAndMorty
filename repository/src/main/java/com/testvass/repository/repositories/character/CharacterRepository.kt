package com.testvass.repository.repositories.character

import com.testvass.datasource.local.character.ICharacterLocalDataSource
import com.testvass.datasource.remote.character.ICharacterRemoteDataSource
import com.testvass.repository.models.CharacterModel
import com.testvass.repository.utils.OnResult
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val characterLocalDataSource: ICharacterLocalDataSource,
    private val characterRemoteDataSource: ICharacterRemoteDataSource
): ICharacterRepository {

    override suspend fun fetch(): OnResult<List<CharacterModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun update(character: CharacterModel): OnResult<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(character: CharacterModel): OnResult<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchById(characterId: Int): OnResult<CharacterModel> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchByName(characterId: Int): OnResult<CharacterModel> {
        TODO("Not yet implemented")
    }
}