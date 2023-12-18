package com.testvass.repository.repositories.character

import com.testvass.datasource.local.character.ICharacterLocalDataSource
import com.testvass.datasource.remote.character.ICharacterRemoteDataSource
import com.testvass.repository.models.CharacterModel
import com.testvass.repository.utils.OnResult
import com.testvass.repository.utils.toEntity
import com.testvass.repository.utils.toModel
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val characterLocalDataSource: ICharacterLocalDataSource,
    private val characterRemoteDataSource: ICharacterRemoteDataSource
): ICharacterRepository {

    override suspend fun fetch(): OnResult<List<CharacterModel>> {
        return try {
            val characters = characterLocalDataSource.fetch().map { it.toModel() }.ifEmpty {
                val newCharacters = characterRemoteDataSource.getCharacters()
                characterLocalDataSource.insert(newCharacters.map { it.toEntity() })
                characterLocalDataSource.fetch().map { it.toModel() }
            }
            OnResult.Success(characters)
        } catch (ioException: IOException) {
            OnResult.Error(ioException)
        } catch (exception: Exception) {
            OnResult.Error(exception)
        }
    }

    override suspend fun update(character: CharacterModel): OnResult<Boolean> {
        characterLocalDataSource.update(character.toEntity())
        return OnResult.Success(true)
    }

    override suspend fun delete(character: CharacterModel): OnResult<Boolean> {
        characterLocalDataSource.delete(character.toEntity())
        return OnResult.Success(true)
    }

    override suspend fun fetchById(characterId: Int): OnResult<CharacterModel> {
        return try {
            val character = characterLocalDataSource.fetchById(characterId).toModel()
            OnResult.Success(character)
        } catch (ioException: IOException) {
            OnResult.Error(ioException)
        } catch (exception: Exception) {
            OnResult.Error(exception)
        }
    }

    override suspend fun fetchByName(name: String): OnResult<List<CharacterModel>> {
        return try {
            val characters = characterRemoteDataSource.getCharacterByName(name).map { it.toModel() }
            characterLocalDataSource.insert(characters.map { it.toEntity() })
            OnResult.Success(characters)
        } catch (ioException: IOException) {
            OnResult.Error(ioException)
        } catch (exception: Exception) {
            OnResult.Error(exception)
        }
    }
}